package com.example.demo.src.favoriteList.dto;

import com.example.demo.src.store.model.Store;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteListResponse {

    private int listId;

    private String listName;

    private Date createAt;

    private int likeCount;

    private String storeIds; // 맛집 리스트에 추가된 store Id값들을 문자열 배열 형태로 저장.

    private List<Store> stores; // 실제 Store 객체의 리스트

}