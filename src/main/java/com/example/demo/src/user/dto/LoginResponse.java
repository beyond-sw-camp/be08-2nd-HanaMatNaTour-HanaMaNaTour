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
    private String profileUrl;

    @Builder
    public LoginResponse(String accessToken, String email, String nickname, String profileUrl){
        this.accessToken = accessToken;
        this.email = email;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }
}