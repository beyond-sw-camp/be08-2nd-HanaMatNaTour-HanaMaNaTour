package com.example.demo.src.user.controller;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.common.util.UserUtil;
import com.example.demo.src.global.jwt.JWTUtil;
import com.example.demo.src.user.dto.*;
import com.example.demo.src.user.service.UserProfileService;
import com.example.demo.src.user.service.UserService;
import com.example.demo.src.user.service.UserSignUpAndFindService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.demo.common.response.BaseResponseStatus.*;
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
    public BaseResponse<userProfileResponseDto> getMyProfile(@PathVariable int userId) {
        String userUUID = UserUtil.getUserUUIdFromAuthentication();
        System.out.println("userUUID : " + userUUID);
        userProfileResponseDto myProfile = userProfileService.getMyProfile(userUUID);
        return new BaseResponse<>(myProfile);
    }

    /**
        로컬 회원가입 API
     */
    @PostMapping("/signup")
    public BaseResponse<SignupRes> signUp(@RequestBody SignupReq signupReq) {
        validateInputUserName(signupReq.getUserName());
        validateInputEmail(signupReq.getUserEmail());
        validateInputPassword(signupReq.getPassword());
        validateEmailRegex(signupReq.getUserEmail());

        SignupRes result = userService.signUp(signupReq);

        return new BaseResponse<>(result);
    }

    // 로컬 로그인
    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginReq loginReq, HttpServletResponse response) {
        validateInputEmail(loginReq.getEmail());
        validateInputPassword(loginReq.getPassword());
        validateEmailRegex(loginReq.getEmail());
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


    private void validateInputUserName(String name) {
        if(name.isEmpty()){
            throw new BaseException(NAME_EMPTY);
        }
    }
    private void validateInputEmail(String email) {
        if (email.isEmpty()) {
            throw new BaseException(EMAIL_EMPTY);
        }
    }
    private void validateInputPassword(String password) {
        if (password.isEmpty()) {
            throw new BaseException(PASSWORD_EMPTY);
        }
    }

    private void validateEmailRegex(String target) {
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(target);
        if (!matcher.find()) {
            throw new BaseException(EMAIL_REGEX_ERROR);
        }

    }

}
