package com.example.demo.src.review.model.service;

import com.example.demo.common.response.BaseResponse;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.review.model.mapper.ReviewMapper;
import com.example.demo.src.review.model.vo.Review;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.common.response.BaseResponseStatus.NOT_FOUND_USER;

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
    public List<Review> getReviewsByStoreId(int storeId) {
        return reviewMapper.selectReviewsByStoreId(storeId);
    }

    @Override
    public Review getReviewByReviewId(int reviewId) {
        return reviewMapper.selectReviewByReviewId(reviewId);
    }

    @Override
    public List<Review> getReviewsByUserUuid(String userUuid) {
        return reviewMapper.selectReviewsByUserUuid(userUuid);
    }

    @Override
    public void saveReview(Review review) {
        Review result= null;

        if(review.getReviewId() != 0) {
            // update
           reviewMapper.updateReview(review);
        } else {
            // insert
           reviewMapper.insertReview(review);
        }
    }

    @Override
    public void deleteReview(Review review) {
        System.out.println(review);

        Review checkReview = reviewMapper.selectReviewByUserUuid(review.getUserUuid(), review.getReviewId());

//        System.out.println(checkReview);
        System.out.println(review.getReviewId());

        if (checkReview.getUserUuid().equals(review.getUserUuid())){
            reviewMapper.deleteReview(review.getReviewId());
        } else {
            new BaseResponse<>(NOT_FOUND_USER);
        }
    }
}