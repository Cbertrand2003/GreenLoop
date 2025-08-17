package com.greenloop.dao;

import com.greenloop.model.Product;
import com.greenloop.util.DBConnection;

import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

public class ProductDAOImpl implements ProductDAO {
    private Product map(ResultSet rs) throws SQLException{
        return new Product(
            rs.getInt("id"),
            rs.getInt("vendor_id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getBigDecimal("price"),
            rs.getInt("stock"),
            rs.getString("sustainability_cert")
        );
    }

    @Override
    public List<Product> findAll() throws Exception {
        String sql = "SELECT * FROM products ORDER BY id DESC";
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            List<Product> out = new ArrayList<>();
            while(rs.next()) out.add(map(rs));
            return out;
        }
    }

    @Override
    public Product findById(int id) throws Exception {
        String sql = "SELECT * FROM products WHERE id=?";
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) return map(rs);
            }
        }
        return null;
    }

    @Override
    public List<Product> search(String q) throws Exception {
        String sql = "SELECT * FROM products WHERE name LIKE ? OR description LIKE ?";
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            String like = "%" + q + "%";
            ps.setString(1, like); ps.setString(2, like);
            List<Product> out = new ArrayList<>();
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()) out.add(map(rs));
            }
            return out;
        }
    }

    @Override
    public int create(Product p) throws Exception {
        String sql = "INSERT INTO products(vendor_id,name,description,price,stock,sustainability_cert) VALUES(?,?,?,?,?,?)";
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, p.getVendorId());
            ps.setString(2, p.getName());
            ps.setString(3, p.getDescription());
            ps.setBigDecimal(4, p.getPrice());
            ps.setInt(5, p.getStock());
            ps.setString(6, p.getSustainabilityCert());
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    @Override
    public void update(Product p) throws Exception {
        String sql = "UPDATE products SET name=?,description=?,price=?,stock=?,sustainability_cert=? WHERE id=?";
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setBigDecimal(3, p.getPrice());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getSustainabilityCert());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM products WHERE id=?")){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public void decrementStock(int productId, int qty) throws Exception {
        String sql = "UPDATE products SET stock = stock - ? WHERE id=? AND stock >= ?";
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, qty);
            ps.setInt(2, productId);
            ps.setInt(3, qty);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Product> findByVendor(int vendorId) throws Exception {
        String sql = "SELECT * FROM products WHERE vendor_id=?";
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, vendorId);
            try(ResultSet rs = ps.executeQuery()){
                List<Product> out = new ArrayList<>();
                while(rs.next()) out.add(map(rs));
                return out;
            }
        }
    }
}
