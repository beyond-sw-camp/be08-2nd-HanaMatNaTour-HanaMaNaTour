package com.example.demo.src.review.model.mapper;

import com.example.demo.src.review.model.dto.Review;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<Review> selectAllReviews();
    List<Review> selectReviewsByRestaurantId(int restaurantId);
    Review selectReviewById(int reviewId);
    void insertReview(Review review); // 리뷰 작성 메서드 추가
    void updateReview(Review review);
    void deleteReview(int reviewId);
}