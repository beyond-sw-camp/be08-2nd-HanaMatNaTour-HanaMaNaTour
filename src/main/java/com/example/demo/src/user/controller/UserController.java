package com.example.demo.src.user.controller;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.global.jwt.JWTUtil;
import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.model.Role;
import com.example.demo.src.user.service.UserProfileService;
import com.example.demo.src.user.service.UserSignUpAndFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.src.user.dto.UserResponseDto.*;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserProfileService userProfileService;
    private final UserSignUpAndFindService userSignUpAndFindService;
    private final JWTUtil jwtUtil;


    // 프로필 관련 -> 닉네임 사라지면서 프로필 수정할 게 없어짐
    @GetMapping("/profile/{userId}")
    public BaseResponse<userProfileResponseDto> getMyProfile(@PathVariable Long userId) {
        userProfileResponseDto myProfile = userProfileService.getMyProfile(userId);
        return new BaseResponse<>(myProfile);
    }

}
