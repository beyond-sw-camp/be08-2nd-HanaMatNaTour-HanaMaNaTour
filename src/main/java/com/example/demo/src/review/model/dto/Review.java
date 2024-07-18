package com.example.demo.src.review.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private int reviewId;
    private int userId;
    private int restaurantId;
    private int reviewScore;
    private String reviewContents;
    private String reviewImg;
    private Date reviewDate;
}