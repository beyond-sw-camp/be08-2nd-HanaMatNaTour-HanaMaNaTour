package com.example.demo.src.hanamoa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreatePostRes {

    private String title;
    private String content; // 내용
    private int storeId; // 음식점 아이디
    private String userUUID; // 작성자
}
