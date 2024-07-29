package com.example.demo.src.review.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto<T> {
    private int status;
    private T data;
    private String message;

    public ReviewResponseDto(HttpStatus status, T data) {
        this.status = status.value();
        this.message = status.getReasonPhrase();
        this.data=data;
    }
}