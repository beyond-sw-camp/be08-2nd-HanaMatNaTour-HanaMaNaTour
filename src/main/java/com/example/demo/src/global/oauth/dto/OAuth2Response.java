package com.example.demo.src.global.oauth.dto;

public interface OAuth2Response {

    // 제공자
    String getProvider();
    // 제공자가 발급해주는 id
    String getProviderId();
    String getEmail();
    String getName();
}
