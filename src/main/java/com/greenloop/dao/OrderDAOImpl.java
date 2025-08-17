package com.greenloop.dao;

import com.greenloop.model.Order;
import com.greenloop.model.OrderItem;
import com.greenloop.util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.math.BigDecimal;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public int createOrder(int customerId) throws Exception {
        String sql = "INSERT INTO orders(customer_id,status,created_at) VALUES(?,?,NOW())";
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, customerId);
            ps.setString(2, "PENDING");
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    @Override
    public void addItem(int orderId, int productId, String name, BigDecimal price, int qty) throws Exception {
        String sql = "INSERT INTO order_items(order_id,product_id,product_name,price,quantity) VALUES(?,?,?,?,?)";
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setString(3, name);
            ps.setBigDecimal(4, price);
            ps.setInt(5, qty);
            ps.executeUpdate();
        }
    }

    @Override
    public void updateStatus(int orderId, String status) throws Exception {
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE orders SET status=? WHERE id=?")){
            ps.setString(1, status);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        }
    }

    @Override
    public Order findById(int orderId) throws Exception {
        String sql = "SELECT * FROM orders WHERE id=?";
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, orderId);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Order o = new Order();
                    o.setId(rs.getInt("id"));
                    o.setCustomerId(rs.getInt("customer_id"));
                    o.setStatus(rs.getString("status"));
                    o.setInvoicePath(rs.getString("invoice_path"));
                    Timestamp ts = rs.getTimestamp("created_at");
                    if(ts!=null) o.setCreatedAt(ts.toLocalDateTime());
                    o.setItems(findItems(orderId));
                    return o;
                }
            }
        }
        return null;
    }

    @Override
    public List<Order> findByCustomer(int customerId) throws Exception {
        String sql = "SELECT * FROM orders WHERE customer_id=? ORDER BY id DESC";
        List<Order> out = new ArrayList<>();
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, customerId);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Order o = new Order();
                    o.setId(rs.getInt("id"));
                    o.setCustomerId(rs.getInt("customer_id"));
                    o.setStatus(rs.getString("status"));
                    o.setInvoicePath(rs.getString("invoice_path"));
                    Timestamp ts = rs.getTimestamp("created_at");
                    if(ts!=null) o.setCreatedAt(ts.toLocalDateTime());
                    o.setItems(findItems(o.getId()));
                    out.add(o);
                }
            }
        }
        return out;
    }

    @Override
    public List<OrderItem> findItems(int orderId) throws Exception {
        String sql = "SELECT * FROM order_items WHERE order_id=?";
        List<OrderItem> items = new ArrayList<>();
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, orderId);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    OrderItem it = new OrderItem();
                    it.setId(rs.getInt("id"));
                    it.setOrderId(rs.getInt("order_id"));
                    it.setProductId(rs.getInt("product_id"));
                    it.setProductName(rs.getString("product_name"));
                    it.setPrice(rs.getBigDecimal("price"));
                    it.setQuantity(rs.getInt("quantity"));
                    items.add(it);
                }
            }
        }
        return items;
    }

    @Override
    public void setInvoicePath(int orderId, String path) throws Exception {
        try(Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE orders SET invoice_path=? WHERE id=?")){
            ps.setString(1, path);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        }
    }
}
