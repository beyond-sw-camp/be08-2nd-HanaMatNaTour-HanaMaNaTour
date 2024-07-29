package com.example.demo.src.user.dto;


import lombok.*;

@ToString
@Getter
@Builder
public class SignupRes {
    private String userName;
    private String userEmail;
    private String password;
    private String userUUID;


}
