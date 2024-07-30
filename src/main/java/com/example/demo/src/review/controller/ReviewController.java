package com.example.demo.src.review.controller;

import com.example.demo.common.response.BaseResponse;
import com.example.demo.common.util.UserUtil;
import com.example.demo.src.review.model.dto.ReviewRequestDto;
import com.example.demo.src.review.model.service.ReviewService;
import com.example.demo.src.review.model.vo.Review;
import com.example.demo.src.store.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.common.response.BaseResponseStatus.NOT_FOUND_METHOD_ERROR;
import static com.example.demo.common.response.BaseResponseStatus.SUCCESS;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final StoreService storeService;

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

    @Operation(summary = "리뷰 조회 by store ID") // 레스토랑 ID로 리뷰 조회
    @GetMapping("/store/{storeId}")
    public BaseResponse<List<Review>> getReviewsByStoreId(
            @Parameter(description = "Store Id", example = "꿈꾸는 돼지") @PathVariable int storeId) {
        List<Review> reviews = reviewService.getReviewsByStoreId(storeId);
        if (reviews != null && !reviews.isEmpty()) {
            return new BaseResponse<>(reviews);
        } else {
            return new BaseResponse<>(NOT_FOUND_METHOD_ERROR);
        }
    }

    @Operation(summary = "작성 리뷰 조회") // 리뷰 ID로 리뷰 조회
    @GetMapping("/{reviewId}")
    public BaseResponse<Review> getReviewByReviewId(
            @Parameter(description = "Review ID", example = "1") @PathVariable int reviewId) {
        Review review = reviewService.getReviewByReviewId(reviewId);
        if (review != null) {
            return new BaseResponse<>(review);
        } else {
            return new BaseResponse<>(NOT_FOUND_METHOD_ERROR);
        }
    }

    @Operation(summary = "Create review") // 리뷰 생성
    @PostMapping("/create")
    public BaseResponse<Review> createReview(@RequestBody ReviewRequestDto requestDto) {
        String userUUId = UserUtil.getUserUUIdFromAuthentication();
        Review review = new Review(requestDto,userUUId);
        reviewService.saveReview(review);
        storeService.updateStoreAverageRating(requestDto.getStoreId()); // 평점 업데이트
        return new BaseResponse<>(review);
    }

    @Operation(summary = "Update review") // 리뷰 수정
    @PostMapping("/update")
    public BaseResponse<Review> updateReview(@RequestBody ReviewRequestDto requestDto) {
        String userUUId = UserUtil.getUserUUIdFromAuthentication();
        Review review = new Review(requestDto,userUUId);
        reviewService.saveReview(review);
        storeService.updateStoreAverageRating(requestDto.getStoreId()); // 평점 업데이트
        if (review != null) {
            return new BaseResponse<>(review);
        } else {
            return new BaseResponse<>(NOT_FOUND_METHOD_ERROR);
        }
    }

    @Operation(summary = "Delete review") // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public BaseResponse<Void> deleteReview(
            @Parameter(description = "Review ID", example = "1") @PathVariable int reviewId) {
        reviewService.deleteReview(reviewId);
        return new BaseResponse<>(SUCCESS);
    }
}
