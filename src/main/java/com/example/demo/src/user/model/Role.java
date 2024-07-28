package com.example.demo.src.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER", "사용자"),
    MANAGER("ROLE_MANAGER", "관리자");

    private final String key;
    private final String title;


}
