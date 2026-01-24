import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private final String url = "jdbc:postgresql://localhost:5432/OOP_project";
    private final String user = "postgres";
    private final String password = "0405";

    // INSERT INTO
    public void saveCustomer(Customer c) {
        String sql = "INSERT INTO customers (customer_id, name, email, phone, bonus) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, c.getCustomerId());
            pstmt.setString(2, c.getName());
            pstmt.setString(3, c.getEmail());
            pstmt.setString(4, c.getPhone());

            // Check if it's a SuperCustomer to save the bonus
            if (c instanceof SuperCustomer) {
                pstmt.setString(5, ((SuperCustomer) c).getBonus());
            } else {
                pstmt.setNull(5, Types.VARCHAR);
            }

            pstmt.executeUpdate();
            System.out.println("Customer saved: " + c.getName());
        } catch (SQLException e) {
            System.err.println("Insert error: " + e.getMessage());
        }
    }
    // SELECT
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String bonus = rs.getString("bonus");
                if (bonus != null) {
                    list.add(new SuperCustomer(rs.getString("customer_id"), rs.getString("name"),
                            rs.getString("email"), rs.getString("phone"), bonus));
                } else {
                    list.add(new Customer(rs.getString("customer_id"), rs.getString("name"),
                            rs.getString("email"), rs.getString("phone")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Read error: " + e.getMessage());
        }
        return list;
    }
    // UPDATE
    public void updateCustomerPhone(String id, String newPhone) {
        String sql = "UPDATE customers SET phone = ? WHERE customer_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPhone);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
            System.out.println("Update successful for ID: " + id);
        } catch (SQLException e) {
            System.err.println("Update error: " + e.getMessage());
        }
    }
    // DELETE
    public void deleteCustomer(String id) {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            System.out.println("Deleted customer: " + id);
        } catch (SQLException e) {
            System.err.println("Delete error: " + e.getMessage());
        }
    }
}