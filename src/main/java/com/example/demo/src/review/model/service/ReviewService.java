package com.example.demo.src.review.model.service;

import com.example.demo.src.review.model.dto.ReviewRequestDto;
import com.example.demo.src.review.model.vo.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    List<Review> getReviewsByRestaurantId(String restaurantId);
    Review getReviewByUserId(String reviewId);
    int saveReview(Review review);
    void deleteReview(String reviewId);
}
