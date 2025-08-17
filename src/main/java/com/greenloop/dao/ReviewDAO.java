package com.greenloop.dao;

import com.greenloop.model.Review;
import java.util.List;

public interface ReviewDAO {
    void add(Review r) throws Exception;
    List<Review> forProduct(int productId) throws Exception;
    double averageForProduct(int productId) throws Exception;
}
