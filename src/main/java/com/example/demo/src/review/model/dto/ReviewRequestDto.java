package com.example.demo.src.review.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {
    private String userId;
    private String restaurantId;
    private int reviewScore;
    private String reviewContents;
    private String reviewImg;
}