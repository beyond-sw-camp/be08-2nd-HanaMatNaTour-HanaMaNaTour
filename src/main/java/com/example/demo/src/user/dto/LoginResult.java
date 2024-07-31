package com.example.demo.src.user.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class LoginResult {

    private String accessToken;
    private String refreshToken;

    private String email;

    private String name;
    private String userUUID;


    @Builder
    public LoginResult(String accessToken, String refreshToken, String email, String name,String userUUID) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.email = email;
        this.name = name;
        this.userUUID=userUUID;
    }

    public LoginResponse toLoginResponse() {
        return LoginResponse.builder()
                .accessToken(this.accessToken)
                .email(this.email)
                .nickname(this.name)
                .userUUID(this.userUUID)
                .build();
    }
}