package com.example.demo.src.user.dto;

import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignupReq { // 회원가입 요청

    private String userName;
    private String userEmail;
    private String password;


    public User toUser(String name, String email, String password, Role role, Provider provider){
        return User.builder()
                .userName(name)
                .userEmail(email)
                .userUUId(UUID.randomUUID().toString())
                .userPassword(password)
                .role(role)
                .provider(provider) // 회원가입 구분
                .build();

    }


}
