package com.example.demo.src.user.converter;

import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.dto.Provider;
import com.example.demo.src.user.dto.UserResponseDto;

import java.util.UUID;

import static com.example.demo.src.user.dto.UserResponseDto.*;

public class UserConverter {

    public User UserInfoDtoToUser(UserInfoResponseDto userInfoResponseDto) {
        User user = new User();
        user.setUserProvideId(userInfoResponseDto.getUserProvideId());
        user.setUserName(userInfoResponseDto.getUserName());
        user.setUserEmail(userInfoResponseDto.getUserEmail());

        return user;
    }

    public User UserSocialDtoToUser(UserSocialLoginResponseDto userSocialLoginResponseDto, Provider socialProvider) {
        User user = new User();
        user.setUserProvideId(userSocialLoginResponseDto.getUserProvideId());
        user.setUserName(userSocialLoginResponseDto.getUserName());
        user.setUserEmail(userSocialLoginResponseDto.getUserEmail());
        user.setRole(userSocialLoginResponseDto.getRole());
        user.setProvider(socialProvider); // 로그인 provider 구분 (로컬, 소셜(카카오,구글,...)
        user.setUserUUId(UUID.randomUUID().toString()); // uuid 랜덤 생성

        return user;
    }
}
