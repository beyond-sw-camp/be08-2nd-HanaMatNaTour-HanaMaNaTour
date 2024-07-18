package com.example.demo.src.review.model.service;

import com.example.demo.src.review.model.dto.Review;
import com.example.demo.src.review.model.mapper.ReviewMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewMapper.selectAllReviews();
    }

    @Override
    public List<Review> getReviewsByRestaurantId(int restaurantId) {
        return reviewMapper.selectReviewsByRestaurantId(restaurantId);
    }

    @Override
    public Review getReviewById(int reviewId) {
        return reviewMapper.selectReviewById(reviewId);
    }

    @Override
    public void insertReview(Review review) {
        reviewMapper.insertReview(review);
    }

    @Override
    public void updateReview(Review review) {
        reviewMapper.updateReview(review);
    }

    @Override
    public void deleteReview(int reviewId) {
        reviewMapper.deleteReview(reviewId);
    }
}