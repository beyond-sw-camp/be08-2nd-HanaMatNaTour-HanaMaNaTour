package com.example.demo.src.user.dto;

import com.example.demo.src.user.domain.User;
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


    public User toUser(String name,String email,String password){
        return User.builder()
                .userName(name)
                .userEmail(email)
                .userProvideId(UUID.randomUUID().toString())
                .password(password)
                .provider(Provider.LOCAL)
                .build();

    }


}
