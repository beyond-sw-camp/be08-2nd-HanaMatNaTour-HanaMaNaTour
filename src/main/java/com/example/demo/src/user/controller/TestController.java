package com.example.demo.src.user.controller;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.user.dao.UserMapper;
import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.model.Role;
import com.example.demo.src.user.service.UserProfileService;
import com.example.demo.src.user.service.UserSignUpAndFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestController {

    private final UserSignUpAndFindService userSignUpAndFindService;
    private final UserProfileService userProfileService;




    @GetMapping("/users/login")
    public String login() {
        return "login";
    }

    @GetMapping("users/nickname")
    public String getNicknamePage(@RequestParam("userProvideId") String userProvideId, Model model) {
        model.addAttribute("userProvideId", userProvideId);
        return "nickname";
    }

    @PatchMapping("/users/nickname/{userProvideId}")
    @ResponseBody
    public String setNicknameAndRole(@RequestParam("userProvideId") String userProvideId,
                                     @RequestParam("nickname") String nickname,
                                     @RequestParam("role") Role role) {
        User user = userSignUpAndFindService.findByProvideId(userProvideId);
        // 닉네임 설정
        if (!user.getUserProvideId().equals(userProvideId) && userSignUpAndFindService.isExistByProvideId(userProvideId)) {
            throw new BaseException(BaseResponseStatus.ALREADY_USE_NICKNAME);
        }
        userSignUpAndFindService.updateNickname(nickname, userProvideId);
        log.info("usernickname : " + nickname);

        // 권한 설정
        if (!user.getRole().equals(role)) {
            userSignUpAndFindService.updateRole(role, userProvideId);
        }

        return "redirect:/main";
    }
}
