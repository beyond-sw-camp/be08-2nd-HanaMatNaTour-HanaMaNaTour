package com.example.demo.src.user.dto;

import com.example.demo.src.user.model.Role;
import lombok.Builder;
import lombok.Data;

public class UserRequestDto {

    @Data
    @Builder
    public static class UserExtraInfoRequestDto {
        private String userNickname;
        private Role role;
    }

    @Data
    @Builder
    public static class UpdateProfileRequestDto {
        private String userNickname;
        private String newNickname;
    }

}
