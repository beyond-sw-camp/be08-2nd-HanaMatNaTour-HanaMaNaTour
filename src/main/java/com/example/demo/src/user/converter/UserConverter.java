package com.example.demo.src.user.converter;

import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.dto.UserResponseDto;

import static com.example.demo.src.user.dto.UserResponseDto.*;

public class UserConverter {

    public User UserInfoDtoToUser(UserInfoResponseDto userInfoResponseDto) {
        User user = new User();
        user.setUserProvideId(userInfoResponseDto.getUserProvideId());
        user.setUserName(userInfoResponseDto.getUserName());
        user.setUserNickname(userInfoResponseDto.getUserNickname());
        user.setUserEmail(userInfoResponseDto.getUserEmail());

        return user;
    }
}
