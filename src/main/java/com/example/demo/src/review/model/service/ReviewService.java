package com.example.demo.src.review.model.service;

import com.example.demo.src.review.model.vo.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    List<Review> getReviewsByStoreId(int storeId);
    void saveReview(Review review);
    void deleteReview(Review review);
    Review getReviewByReviewId(int reviewId);
    List<Review> getReviewsByUserUuid(String userUuid);
}
