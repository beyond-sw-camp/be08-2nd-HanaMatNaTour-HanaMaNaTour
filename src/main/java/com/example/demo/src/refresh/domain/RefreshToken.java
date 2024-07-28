package com.example.demo.src.refresh.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {

    private Long refreshTokenId;
    private String userProvideId;
    private String refreshToken;
    private String expiration;
}
