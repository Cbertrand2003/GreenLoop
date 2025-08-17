package com.greenloop.dao;

import com.greenloop.model.Role;
import com.greenloop.model.User;
import com.greenloop.util.DBConnection;

import java.sql.*;

public class UserDAOImpl implements UserDAO {
    @Override
    public User findByEmail(String email) throws Exception {
        String sql = "SELECT id,name,email,password_hash,salt,role FROM users WHERE email=?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, email);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setName(rs.getString("name"));
                    u.setEmail(rs.getString("email"));
                    u.setPasswordHash(rs.getString("password_hash"));
                    u.setSalt(rs.getString("salt"));
                    u.setRole(Role.valueOf(rs.getString("role")));
                    return u;
                }
            }
        }
        return null;
    }

    @Override
    public User findById(int id) throws Exception {
        String sql = "SELECT id,name,email,password_hash,salt,role FROM users WHERE id=?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setName(rs.getString("name"));
                    u.setEmail(rs.getString("email"));
                    u.setPasswordHash(rs.getString("password_hash"));
                    u.setSalt(rs.getString("salt"));
                    u.setRole(Role.valueOf(rs.getString("role")));
                    return u;
                }
            }
        }
        return null;
    }

    @Override
    public int create(String name, String email, String hash, String salt, Role role) throws Exception {
        String sql = "INSERT INTO users(name,email,password_hash,salt,role) VALUES(?,?,?,?,?)";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, hash);
            ps.setString(4, salt);
            ps.setString(5, role.name());
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }
}
