package com.example.demo.src.review.model.mapper;

import com.example.demo.src.review.model.vo.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<Review> selectAllReviews();
    List<Review> selectReviewsByStoreId(int storeId);
    Review selectReviewByReviewId(int reviewId);
    void updateReview(Review review);
    void insertReview(Review review);
    void deleteReview(int reviewId);

}