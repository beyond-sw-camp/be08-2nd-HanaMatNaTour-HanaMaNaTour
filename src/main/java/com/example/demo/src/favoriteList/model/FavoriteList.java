package com.example.demo.src.favoriteList.model;

import com.example.demo.src.store.model.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteList {
    private int listId;

    private String listName;

    private Date createAt;

    private List<Store> stores;

    private int likeCount;
}
