package com.example.demo.src.user.domain;

import com.example.demo.src.user.dto.Provider;
import com.example.demo.src.user.model.Role;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long userId;

    @Setter
    private String userProvideId;
    @Setter
    private String userName;
    @Setter
    private String userEmail;
    @Setter
    private Role role;

    @Setter
    private String refreshToken;
    @Setter
    private String expiration;

    private String password;

    private Provider provider;
}