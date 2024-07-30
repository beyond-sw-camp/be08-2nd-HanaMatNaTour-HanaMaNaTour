package com.example.demo.src.foodlist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;

    private String userUuid;

    private String userName;

    public User(String userUuid, String userName) {
        this.userUuid = userUuid;
        this.userName = userName;
    }

}
