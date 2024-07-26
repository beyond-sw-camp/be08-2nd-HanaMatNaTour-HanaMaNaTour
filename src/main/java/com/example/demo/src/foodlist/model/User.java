package com.example.demo.src.foodlist.model;

import lombok.Getter;

@Getter
public class User {

    private int id;

    private String userName;

    public User(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
