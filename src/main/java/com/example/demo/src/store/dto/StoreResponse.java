package com.example.demo.src.store.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StoreResponse {
    private int storeId;

    private String storeName;

    private String storeAddress;

    private String category;

    private int likeCount;

    private double AvgRating;

    private LocalDateTime updateAt;
}
