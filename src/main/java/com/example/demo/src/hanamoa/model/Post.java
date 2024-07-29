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

    private int postId;

    private String title;

    private String content;

    private String userUuid; // 작성자 ID

    private int storeId; // 위치 ID

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int viewCount; // 조회수

    // UI에 필요한 경우에만 포함

    private String userName; // 작성자 이름

    private String storeName; // 위치 이름

    // PostRequest를 위해 추가된 생성자
    public Post(String title, String content, String userUuid, int storeId) {
        this.title = title;
        this.content = content;
        this.userUuid = userUuid;
        this.storeId = storeId;
    }
}
