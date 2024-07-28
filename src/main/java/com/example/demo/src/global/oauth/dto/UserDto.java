package com.example.demo.src.global.oauth.dto;

import com.example.demo.src.user.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto { // 소셜로그인에 쓰는 유저 dto
    private Role role;
    private String userName; // 이름
    private String userProvideId; // 자체id
}
