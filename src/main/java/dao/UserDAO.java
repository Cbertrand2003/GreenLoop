package dao;

import model.User;

import utils.DBUtil;
import utils.PasswordUtil;
import java.sql.*;

public class UserDAO {
    private static final String SELECT_USER = 
        "SELECT username, password, role, email, is_active FROM users WHERE username = ?";
    private static final String UPDATE_PASSWORD = 
        "UPDATE users SET password = ? WHERE email = ?";

    public User getUserByUsername(String username) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_USER)) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("email"),
                    rs.getBoolean("is_active")
                );
            }
        } catch (SQLException e) {
            System.err.println("Database error in getUserByUsername: " + e.getMessage());
        }
        return null;
    }

    public boolean updatePassword(String email, String newPassword) {
        String hashed = PasswordUtil.hash(newPassword);
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_PASSWORD)) {
            
            stmt.setString(1, hashed);
            stmt.setString(2, email);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Password update failed: " + e.getMessage());
            return false;
        }
    }
}
