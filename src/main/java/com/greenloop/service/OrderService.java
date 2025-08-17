package com.greenloop.service;

import com.greenloop.dao.OrderDAO;
import com.greenloop.dao.OrderDAOImpl;
import com.greenloop.model.*;
import java.util.List;

public class OrderService {
    private final OrderDAO dao = new OrderDAOImpl();

    public int createFromCart(int customerId, ShoppingCart cart) throws Exception {
        int orderId = dao.createOrder(customerId);
        for(CartItem ci: cart.getItems()){
            dao.addItem(orderId, ci.getProduct().getId(), ci.getProduct().getName(), ci.getProduct().getPrice(), ci.getQuantity());
        }
        dao.updateStatus(orderId, "PAID"); // Simulated payment success
        return orderId;
    }

    public Order get(int id) throws Exception { return dao.findById(id); }
    public List<Order> forCustomer(int customerId) throws Exception { return dao.findByCustomer(customerId); }
    public void setInvoice(int orderId, String path) throws Exception { dao.setInvoicePath(orderId, path); }
}
