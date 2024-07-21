package com.example.demo.src.hanamoa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private int id;

    private String title;

    private String content;

    private int userId; // 작성자 ID

    private int locationId; // 위치 ID

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int viewCount; // 조회수

    // UI에 필요한 경우에만 포함

    private String userName; // 작성자 이름

    private String locationName; // 위치 이름

    // PostRequest를 위해 추가된 생성자
    public Post(String title, String content, int userId, int locationId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.locationId = locationId;
    }
}
