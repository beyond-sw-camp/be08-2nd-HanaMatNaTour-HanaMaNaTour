package com.example.demo.src.review.model.mapper;

import com.example.demo.src.review.model.vo.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<Review> selectAllReviews();
    List<Review> selectReviewsByStoreId(int storeId);
    Review selectReviewByReviewId(int reviewId);
    void updateReview(Review review);
    void insertReview(Review review);
    void deleteReview(int reviewId);
    List<Review> selectReviewsByUserUuid(String userUuid);
    Review selectReviewByUserUuid(@Param("userUuid") String userUuid,@Param("reviewId") int reviewId);
}