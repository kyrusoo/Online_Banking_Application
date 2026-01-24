import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private final String url = "jdbc:postgresql://localhost:5432/OOP_project";
    private final String user = "postgres";
    private final String password = "0405";

    // INSERT INTO (Save a new account)
    public void saveAccount(BankAccount account) {
        String sql = "INSERT INTO bank_accounts (account_number, balance, account_type, customer_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, account.getAccountNumber());
            pstmt.setDouble(2, account.getBalance());
            pstmt.setString(3, account.getAccountType());
            pstmt.setString(4, account.getCustomerId());

            pstmt.executeUpdate();
            System.out.println("Account " + account.getAccountNumber() + " saved successfully.");
        } catch (SQLException e) {
            System.err.println("Error saving account: " + e.getMessage());
        }
    }

    // READ (Get all accounts for a specific customer)
    public List<BankAccount> getAccountsByCustomer(String customerId) {
        List<BankAccount> accounts = new ArrayList<>();
        String sql = "SELECT * FROM bank_accounts WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customerId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                accounts.add(new BankAccount(
                        rs.getString("account_number"),
                        rs.getDouble("balance"),
                        rs.getString("account_type"),
                        rs.getString("customer_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching accounts: " + e.getMessage());
        }
        return accounts;
    }

    // UPDATE (Update balance after deposit/withdrawal)
    public void updateBalance(String accountNumber, double newBalance) {
        String sql = "UPDATE bank_accounts SET balance = ? WHERE account_number = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, accountNumber);
            pstmt.executeUpdate();
            System.out.println("Balance updated for account: " + accountNumber);
        } catch (SQLException e) {
            System.err.println("Error updating balance: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteAccount(String accountNumber) {
        String sql = "DELETE FROM bank_accounts WHERE account_number = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, accountNumber);
            pstmt.executeUpdate();
            System.out.println("Account " + accountNumber + " deleted.");
        } catch (SQLException e) {
            System.err.println("Error deleting account: " + e.getMessage());
        }
    }
}