package com.example.demo.src.user.controller;

import com.example.demo.src.user.dao.UserMapper;
import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.model.Role;
import com.example.demo.src.user.service.UserProfileService;
import com.example.demo.src.user.service.UserSignUpAndFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final UserSignUpAndFindService userSignUpAndFindService;
    private final UserProfileService userProfileService;

/*
    @GetMapping("/login/oauth2/code/google")
    public String loginWait() {
        return "/wait";
    }
*/

    @GetMapping("/users/login")
    public String login() {
        return "login";
    }

    @GetMapping("users/nickname")
    public String getNicknamePage(@RequestParam("userProvideId") String userProvideId, Model model) {
        model.addAttribute("userProvideId", userProvideId);
        return "nickname";
    }

    @PostMapping("/users/nickname")
    public String setNicknameAndRole(@RequestParam("userProvideId") String userProvideId,
                                     @RequestParam("nickname") String nickname,
                                     @RequestParam("role") String role) {
        User user = userSignUpAndFindService.findByProvideId(userProvideId);
        if (user != null) {
            user.setUserName(nickname);
            try {
                user.setRole(Role.valueOf(role.toUpperCase()));
            } catch (IllegalArgumentException e) {
                // 로그를 남기고, 사용자가 잘못된 역할을 입력한 경우를 처리
                return "error"; // 적절한 에러 페이지로 리다이렉트
            }
            // 사용자 정보를 저장
            userSignUpAndFindService.save(user);
        }
        return "redirect:/home";
    }
}
