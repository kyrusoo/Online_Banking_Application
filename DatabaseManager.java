import java.sql.*;

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
}