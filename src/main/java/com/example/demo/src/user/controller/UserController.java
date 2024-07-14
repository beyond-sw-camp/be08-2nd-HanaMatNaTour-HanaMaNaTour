package com.example.demo.src.user.controller;

import com.example.demo.src.user.service.UserProfileService;
import com.example.demo.src.user.service.UserSignUpAndFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserProfileService userProfileService;
    private final UserSignUpAndFindService userSignUpAndFindService;

    @GetMapping("/login")
    public String login() { // 이건 그냥 소셜로그인 code받을라고 ..
        return "login";
    }
}
