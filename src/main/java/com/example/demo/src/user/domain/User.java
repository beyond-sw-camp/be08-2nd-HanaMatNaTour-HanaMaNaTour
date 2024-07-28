package com.example.demo.src.user.domain;

import com.example.demo.src.user.dto.Provider;
import com.example.demo.src.user.model.Role;
import lombok.*;

@ToString
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long userId;

    private String userProvideId;

    private String userName;

    private String userEmail;

    private Role role;

    private String refreshToken;

    private String expiration;

    private Provider provider;

    private String userUUId;

    private String userPassword;
}