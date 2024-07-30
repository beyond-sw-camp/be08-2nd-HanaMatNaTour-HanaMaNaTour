package com.example.demo.src.review.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {
    private String userUuid;
    private int storeId;
    private int reviewScore;
    private String reviewContents;
    private String reviewImg;
}