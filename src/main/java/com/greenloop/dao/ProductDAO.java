package com.greenloop.dao;

import com.greenloop.model.Product;
import java.util.List;

public interface ProductDAO {
    List<Product> findAll() throws Exception;
    Product findById(int id) throws Exception;
    List<Product> search(String q) throws Exception;
    int create(Product p) throws Exception;
    void update(Product p) throws Exception;
    void delete(int id) throws Exception;
    void decrementStock(int productId, int qty) throws Exception;
    List<Product> findByVendor(int vendorId) throws Exception;
}
