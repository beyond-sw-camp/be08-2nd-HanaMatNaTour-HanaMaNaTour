package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    private Long storeId;

    private String storeName;

    private String storeAddress;

    private String category;

    private int likeCount;

    // StoreRequest를 위해 추가된 생성자
    public Store(String storeName, String storeAddress, String category) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.category = category;
    }
}
