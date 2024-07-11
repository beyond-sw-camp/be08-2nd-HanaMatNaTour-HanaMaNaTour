package com.example.demo.src.user.domain;

import com.example.demo.src.user.model.Authority;
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
    private Authority userAuthority;
    private String refreshToken;

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public void setAuthority(Authority userAuthority) {
        this.userAuthority = userAuthority;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


}