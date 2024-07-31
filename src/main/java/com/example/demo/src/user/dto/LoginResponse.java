package com.example.demo.src.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String email;
    private String nickname;
    private String userUUID;

    @Builder
    public LoginResponse(String accessToken, String email, String nickname, String userUUID){
        this.accessToken = accessToken;
        this.email = email;
        this.nickname = nickname;
        this.userUUID = userUUID;
    }
}