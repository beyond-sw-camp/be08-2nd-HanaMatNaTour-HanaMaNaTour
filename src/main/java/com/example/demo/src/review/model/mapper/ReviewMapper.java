package com.example.demo.src.review.model.mapper;

import com.example.demo.src.review.model.vo.Review;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<Review> selectAllReviews();
    List<Review> selectReviewsByRestaurantId(String restaurantId);
    Review selectReviewByUserId(String reviewId);
    int updateReview(Review review);
    int insertReview(Review review);
    void deleteReview(String reviewId);

}