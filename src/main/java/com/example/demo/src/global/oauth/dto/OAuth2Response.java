package com.example.demo.src.global.oauth.dto;

public interface OAuth2Response {

    String getProvider(); // 공급자 이름 (google, kakao etc)
    String getProviderId();
    String getEmail();
    String getName();
}
