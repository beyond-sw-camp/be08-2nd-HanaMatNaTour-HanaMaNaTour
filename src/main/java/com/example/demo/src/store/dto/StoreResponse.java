package com.example.demo.src.store.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StoreResponse {
    private Long storeId;

    private String storeName;

    private String storeAddress;

    private String category;

    private int likeCount;
}
