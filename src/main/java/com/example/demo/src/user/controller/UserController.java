package com.example.demo.src.user.controller;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.chat.dto.LoginReq;
import com.example.demo.src.global.jwt.JWTUtil;
import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.dto.LoginResponse;
import com.example.demo.src.user.dto.LoginResult;
import com.example.demo.src.user.dto.SignupReq;
import com.example.demo.src.user.dto.UserResponseDto;
import com.example.demo.src.user.model.Role;
import com.example.demo.src.user.service.UserProfileService;
import com.example.demo.src.user.service.UserService;
import com.example.demo.src.user.service.UserSignUpAndFindService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
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
    private final UserService userService;
    

    // 프로필 관련 -> 닉네임 사라지면서 프로필 수정할 게 없어짐
    @GetMapping("/profile/{userId}")
    public BaseResponse<userProfileResponseDto> getMyProfile(@PathVariable Long userId) {
        userProfileResponseDto myProfile = userProfileService.getMyProfile(userId);
        return new BaseResponse<>(myProfile);
    }

    /**
        로컬 회원가입 API
     */
    @PostMapping("/signup")
    public String signUp(@RequestBody SignupReq signupReq) {
        // todo : validation 처리
        userService.signUp(signupReq);

        return "회원가입을 완료했습니다.";
    }

    // 로컬 로그인
    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginReq loginReq, HttpServletResponse response) {
        // todo : 형식적 validation

        //  토큰 발급, 유저 정보 로드
        LoginResult loginResult = userService.login(loginReq);
        String accessToken = loginResult.getAccessToken();
        String refreshToken = loginResult.getRefreshToken();

        // 엑세스 토큰 헤더에 삽입
        jwtUtil.addAccessTokenInHeader(accessToken,response);

        // 리프레시 토큰 쿠키에 삽입
        jwtUtil.addRefreshTokenInCookie(refreshToken,response);


        // 로그인 응답 객체 생성
        LoginResponse loginResponse = loginResult.toLoginResponse();

        return new BaseResponse<>(loginResponse);
    }
}
