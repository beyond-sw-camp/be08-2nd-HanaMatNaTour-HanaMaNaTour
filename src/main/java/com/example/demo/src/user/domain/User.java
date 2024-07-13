package com.example.demo.src.user.domain;

import com.example.demo.src.user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long userId;
    private String userName;
    private String userNickname;
    private String userEmail;
    private Role role;
    private String refreshToken;

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


}