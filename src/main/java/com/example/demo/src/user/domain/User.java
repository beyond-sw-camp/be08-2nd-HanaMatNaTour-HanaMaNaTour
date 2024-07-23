package com.example.demo.src.user.domain;

import com.example.demo.src.user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long userId;

    @Setter
    private String userProvideId;
    @Setter
    private String userName;
    @Setter
    private String userNickname;
    @Setter
    private String userEmail;
    @Setter
    private Role role;

    @Setter
    private String refreshToken;
    @Setter
    private String expiration;

}