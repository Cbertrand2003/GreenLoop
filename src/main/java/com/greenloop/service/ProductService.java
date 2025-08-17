package com.greenloop.service;

import com.greenloop.dao.ProductDAO;
import com.greenloop.dao.ProductDAOImpl;
import com.greenloop.model.Product;

import java.util.List;

public class ProductService {
    private final ProductDAO dao = new ProductDAOImpl();

    public List<Product> list(String q) throws Exception {
        if(q==null || q.isBlank()) return dao.findAll();
        return dao.search(q.trim());
    }

    public int create(Product p) throws Exception { return dao.create(p); }
    public void update(Product p) throws Exception { dao.update(p); }
    public void delete(int id) throws Exception { dao.delete(id); }
    public Product get(int id) throws Exception { return dao.findById(id); }
    public void decrementStock(int id, int qty) throws Exception { dao.decrementStock(id, qty); }
    public List<Product> byVendor(int vendorId) throws Exception { return dao.findByVendor(vendorId); }
}
