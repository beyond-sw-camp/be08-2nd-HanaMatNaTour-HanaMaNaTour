package com.example.demo.src.review.model.service;

import com.example.demo.src.review.model.dto.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    List<Review> getReviewsByRestaurantId(int restaurantId);
    Review getReviewById(int reviewId);
    void insertReview(Review review); // 리뷰 작성 메서드 추가
    void updateReview(Review review);
    void deleteReview(int reviewId);
}