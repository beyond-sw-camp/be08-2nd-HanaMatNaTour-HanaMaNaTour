package com.example.demo.src.user.dto;

import com.example.demo.src.user.model.Authority;
import lombok.Builder;
import lombok.Data;

public class UserRequestDto {

    @Data
    @Builder
    public static class UserExtraInfoRequestDto {
        private String userNickname;
        private Authority authority;
    }

}
