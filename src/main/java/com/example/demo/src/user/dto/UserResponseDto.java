package com.example.demo.src.user.dto;

import com.example.demo.src.user.model.Role;
import lombok.Builder;
import lombok.Data;

public class UserResponseDto {


    @Data
    @Builder
    public static class UserInfoResponseDto {
        private String userProvideId;
        private String userName;
        private String userNickname;
        private String userEmail;
        private Role role;
        private String refreshToken;
    }


    // 프로필 관련
    @Data
    @Builder
    public static class userProfileResponseDto {
        private String userName;
        private String userNickname;
        private String userEmail;
        private int numOfReview;
//        private List<Review> reviewList;
        private int numOfList;
//        private List<List> listList;
    }
}
