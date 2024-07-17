package com.example.demo.src.global.oauth.dto;

import java.util.Map;

public class GoogleResponse implements OAuth2Response{

    private final Map<String, Object> attribute; // 속성명, 값

    public GoogleResponse(Map<String, Object> attribute) {
        this.attribute = attribute; // response 안에 있는 데이터 받아 오기
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getProviderId() {
        return attribute.get("sub").toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }
}
