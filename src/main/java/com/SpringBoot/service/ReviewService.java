package com.SpringBoot.service;

import com.SpringBoot.model.Product;
import com.SpringBoot.model.Review;
import com.SpringBoot.model.User;
import com.SpringBoot.request.CreateReviewRequest;


import java.util.List;

public interface ReviewService {

    Review createReview(CreateReviewRequest req,
                        User user,
                        Product product

    );
    List<Review> getReviewByProductId(Long productId);

    Review updateReview(Long reviewId,String reviewText,double rating,Long userId) throws Exception;

    void deleteReview(Long reviewId,Long userId) throws Exception;

    Review getReviewById(Long reviewId) throws Exception;
}
