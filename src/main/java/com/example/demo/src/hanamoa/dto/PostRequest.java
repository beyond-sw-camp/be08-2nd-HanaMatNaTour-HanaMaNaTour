package com.example.demo.src.hanamoa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    private String title;

    private String content;

    private String userUuid; // 작성자 ID

    private int storeId; // 위치 ID
}
