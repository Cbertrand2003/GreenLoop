package com.greenloop.service;

import com.greenloop.dao.ReviewDAO;
import com.greenloop.dao.ReviewDAOImpl;
import com.greenloop.model.Review;

import java.util.List;

public class ReviewService {
    private final ReviewDAO dao = new ReviewDAOImpl();

    public void add(Review r) throws Exception { dao.add(r); }
    public List<Review> forProduct(int productId) throws Exception { return dao.forProduct(productId); }
    public double average(int productId) throws Exception { return dao.averageForProduct(productId); }
}
