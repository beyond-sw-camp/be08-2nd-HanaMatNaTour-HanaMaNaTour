package com.example.demo.src.user.controller;

import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.dto.UserRequestDto;
import com.example.demo.src.user.dto.UserResponseDto;
import com.example.demo.src.user.service.UserProfileService;
import com.example.demo.src.user.service.UserSignUpAndFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.src.user.dto.UserRequestDto.*;
import static com.example.demo.src.user.dto.UserResponseDto.*;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserProfileService userProfileService;
    private final UserSignUpAndFindService userSignUpAndFindService;



    // 프로필 관련
    @GetMapping("/profile")
    public userProfileResponseDto getMyProfile(@RequestParam String userNickname) {
        return userProfileService.getMyProfile(userNickname);
    }

    @PatchMapping("/profile")
    public userProfileResponseDto updateMyProfile(@RequestBody UpdateProfileRequestDto updateProfileRequestDto) {
        User user = userSignUpAndFindService.findByUserNickname(updateProfileRequestDto.getUserNickname());
        return userProfileService.editMyProfile(user.getUserId(), updateProfileRequestDto.getNewNickname());
    }
}
