package com.example.demo.src.hanamoa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostResponse {

    private int id;

    private String title;

    private String content;

    private String userUuid;

    private String userName; // 작성자 이름

    private int storeId;

    private String storeName; // 위치 이름

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int viewCount; // 조회수
}
