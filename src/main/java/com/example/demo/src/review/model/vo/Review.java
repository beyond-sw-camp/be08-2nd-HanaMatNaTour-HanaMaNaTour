package com.example.demo.src.review.model.vo;

import com.example.demo.src.review.model.dto.ReviewRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private String reviewId;
    private String userId;
    private String restaurantId;
    private int reviewScore;
    private String reviewContents;
    private String reviewImg;
    private Date reviewDate;

    // ReviewRequestDto를 통해 Review 초기화
    public Review(ReviewRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.restaurantId = requestDto.getRestaurantId();
        this.reviewScore = requestDto.getReviewScore();
        this.reviewContents = requestDto.getReviewContents();
        this.reviewImg = requestDto.getReviewImg();
        this.reviewDate = new Date(); // 기본적으로 현재 날짜로 설정
    }

    // ReviewRequestDto를 통해 Review 업데이트
    public void updateFromRequestDto(ReviewRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.restaurantId = requestDto.getRestaurantId();
        this.reviewScore = requestDto.getReviewScore();
        this.reviewContents = requestDto.getReviewContents();
        this.reviewImg = requestDto.getReviewImg();
    }
}