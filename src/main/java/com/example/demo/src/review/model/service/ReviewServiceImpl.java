package com.example.demo.src.review.model.service;

import com.example.demo.src.review.model.dto.ReviewRequestDto;
import com.example.demo.src.review.model.mapper.ReviewMapper;
import com.example.demo.src.review.model.vo.Review;
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
    public List<Review> getReviewsByRestaurantId(String restaurantId) {
        return reviewMapper.selectReviewsByRestaurantId(restaurantId);
    }

    @Override
    public Review getReviewByUserId(String reviewId) {
        return reviewMapper.selectReviewByUserId(reviewId);
    }

    @Override
    public int saveReview(Review review) {
        int result = 0;

        if(review.getReviewId() != null) {
            // update
            result = reviewMapper.updateReview(review);
        } else {
            // insert
            result = reviewMapper.insertReview(review);
        }

        return result;
    }

    @Override
    public void deleteReview(String reviewId) {
        reviewMapper.deleteReview(reviewId);
    }
}