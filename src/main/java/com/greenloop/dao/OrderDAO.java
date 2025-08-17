package com.greenloop.dao;

import com.greenloop.model.Order;
import com.greenloop.model.OrderItem;

import java.util.List;

public interface OrderDAO {
    int createOrder(int customerId) throws Exception;
    void addItem(int orderId, int productId, String name, java.math.BigDecimal price, int qty) throws Exception;
    void updateStatus(int orderId, String status) throws Exception;
    Order findById(int orderId) throws Exception;
    List<Order> findByCustomer(int customerId) throws Exception;
    List<OrderItem> findItems(int orderId) throws Exception;
    void setInvoicePath(int orderId, String path) throws Exception;
}
