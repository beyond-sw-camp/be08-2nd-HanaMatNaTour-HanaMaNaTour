package com.example.demo.src.store.dto;

import com.example.demo.src.menu.Menu;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StoreResponse {
    private int storeId;
    private String storeName;
    private String storeAddress;
    private String category;
    private int likeCount;
    private double avgRating;
    private LocalDateTime updateAt;
    List<Menu> menuList;
}
