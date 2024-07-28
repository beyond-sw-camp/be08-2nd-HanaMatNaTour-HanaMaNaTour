package com.example.demo.src.review.controller;

import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.review.model.dto.ReviewRequestDto;
import com.example.demo.src.review.model.vo.Review;
import com.example.demo.src.review.model.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.common.response.BaseResponseStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(summary = "전체 리뷰 조회") // 전체 리뷰 조회
    @GetMapping
    public BaseResponse<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        if (reviews != null && !reviews.isEmpty()) {
            return new BaseResponse<>(reviews);
        } else {
            return new BaseResponse<>(NOT_FOUND_METHOD_ERROR);
        }
    }

    @Operation(summary = "리뷰 조회 by restaurant ID") // 레스토랑 ID로 리뷰 조회
    @GetMapping("/restaurant/{restaurantId}")
    public BaseResponse<List<Review>> getReviewsByRestaurantId(
            @Parameter(description = "Restaurant ID", example = "꿈꾸는 돼지") @PathVariable String restaurantId) {
        List<Review> reviews = reviewService.getReviewsByRestaurantId(restaurantId);
        if (reviews != null && !reviews.isEmpty()) {
            return new BaseResponse<>(reviews);
        } else {
            return new BaseResponse<>(NOT_FOUND_METHOD_ERROR);
        }
    }

    @Operation(summary = "작성 리뷰 조회") // 리뷰 ID로 리뷰 조회
    @GetMapping("/{reviewId}")
    public BaseResponse<Review> getReviewByUserId(
            @Parameter(description = "Review ID", example = "1") @PathVariable String reviewId) {
        Review review = reviewService.getReviewByUserId(reviewId);
        if (review != null) {
            return new BaseResponse<>(review);
        } else {
            return new BaseResponse<>(NOT_FOUND_METHOD_ERROR);
        }
    }

    @Operation(summary = "Create review") // 리뷰 생성
    @PostMapping("/create")
    public BaseResponse<Review> createReview(@RequestBody ReviewRequestDto requestDto) {
        Review review = new Review(requestDto);
        reviewService.saveReview(review);
        return new BaseResponse<>(review);
    }

    @Operation(summary = "Update review") // 리뷰 수정
    @PostMapping("/update")
    public BaseResponse<Review> updateReview(@RequestBody ReviewRequestDto requestDto) {
        Review review = new Review(requestDto);
        reviewService.saveReview(review);
        if (review != null) {
            return new BaseResponse<>(review);
        } else {
            return new BaseResponse<>(NOT_FOUND_METHOD_ERROR);
        }
    }

    @Operation(summary = "Delete review") // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public BaseResponse<Void> deleteReview(
            @Parameter(description = "Review ID", example = "1") @PathVariable String reviewId) {
        reviewService.deleteReview(reviewId);
        return new BaseResponse<>(SUCCESS);
    }
}
