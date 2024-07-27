package com.example.demo.src.user.controller;

import com.example.demo.common.exceptions.BaseException;
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


    @GetMapping("/nickname")
    @ResponseBody
    public ResponseEntity<User> getUser(@RequestHeader("access") String access) {
        if (jwtUtil.isExpired(access)) throw new BaseException(BaseResponseStatus.EXPIRED_TOKEN);
        String userProvideId = jwtUtil.getUserProvideId(access);
        User user = userSignUpAndFindService.findByProvideId(userProvideId);

        return ResponseEntity.ok(user);
    }



    // 프로필 관련
    @GetMapping("/profile/{userProvideId}")
    public userProfileResponseDto getMyProfile(@PathVariable String userProvideId) {
        return userProfileService.getMyProfile(userProvideId);
    }

}
