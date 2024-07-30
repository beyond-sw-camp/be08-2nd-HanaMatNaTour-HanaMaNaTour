package com.example.demo.src.foodlist.model;


import com.example.demo.src.store.model.Store;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodList {

    private int foodlistId;

    private String foodlistName;

    private int ListLikeCount;

    private User user;

    private Store store;



    public FoodList(String foodlistName, int listLikeCount, User user, Store store) {
        this.foodlistName = foodlistName;
        this.ListLikeCount = listLikeCount;
        this.user = user;
        this.store = store;
    }

    public FoodList(String foodlistName) {
        this.foodlistName = foodlistName;
    }
}
