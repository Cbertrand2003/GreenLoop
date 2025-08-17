package com.greenloop.dao;

import com.greenloop.model.Review;
import com.greenloop.util.DBConnection;

import java.sql.*;
import java.util.*;

public class ReviewDAOImpl implements ReviewDAO {
    @Override
    public void add(Review r) throws Exception {
        String sql = "INSERT INTO reviews(product_id,customer_id,rating,comment,date) VALUES(?,?,?,?,?)";
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, r.getProductId());
            ps.setInt(2, r.getCustomerId());
            ps.setInt(3, r.getRating());
            ps.setString(4, r.getComment());
            ps.setDate(5, java.sql.Date.valueOf(r.getDate()));
            ps.executeUpdate();
        }
    }

    @Override
    public List<Review> forProduct(int productId) throws Exception {
        String sql = "SELECT * FROM reviews WHERE product_id=? ORDER BY id DESC";
        List<Review> out = new ArrayList<>();
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, productId);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Review r = new Review();
                    r.setId(rs.getInt("id"));
                    r.setProductId(rs.getInt("product_id"));
                    r.setCustomerId(rs.getInt("customer_id"));
                    r.setRating(rs.getInt("rating"));
                    r.setComment(rs.getString("comment"));
                    r.setDate(rs.getDate("date").toLocalDate());
                    out.add(r);
                }
            }
        }
        return out;
    }

    @Override
    public double averageForProduct(int productId) throws Exception {
        String sql = "SELECT AVG(rating) AS avg_rating FROM reviews WHERE product_id=?";
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, productId);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) return rs.getDouble("avg_rating");
            }
        }
        return 0.0;
    }
}
