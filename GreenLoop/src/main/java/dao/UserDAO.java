package dao;

import model.User;
import java.sql.*;

public class UserDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/greenloop";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "your_password";

    private static final String SELECT_USER_BY_USERNAME =
        "SELECT username, password, role, email, is_active FROM users WHERE username = ?";

    public User getUserByUsername(String username) {
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String password = rs.getString("password");
                String role = rs.getString("role");
                String email = rs.getString("email");
                boolean active = rs.getBoolean("is_active");

                return new User(username, password, role, email, active);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}