package com.example.demo.src.chat;

import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.chat.dto.LoginReq;
import com.example.demo.src.chat.dto.LoginRes;
import com.example.demo.src.chat.dto.SignupReq;
import com.example.demo.src.chat.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @PostMapping("")
    public BaseResponse<String> signup(@RequestBody SignupReq signupReq){
            userService.signup(signupReq);
            return new BaseResponse<>("회원가입에 성공했습니다.");

    }

    @GetMapping("")
    public BaseResponse<LoginRes> login(@RequestBody LoginReq loginReq){
        LoginRes result = userService.login(loginReq);

        return new BaseResponse<>(result);
    }
}
