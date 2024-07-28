package com.example.demo.src.chat;

import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.chat.dto.LoginReq;
import com.example.demo.src.chat.dto.LoginRes;
import com.example.demo.src.chat.dto.SignupReq;
import com.example.demo.src.chat.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;


    @PostMapping("/signup")
    public BaseResponse<String> signup(@RequestBody SignupReq signupReq){
        System.out.println("singup 실행");
            userService.signup(signupReq);
            return new BaseResponse<>("회원가입에 성공했습니다.");

    }

    @PostMapping("/login")
    public BaseResponse<LoginRes> login(@RequestBody LoginReq loginReq){
        LoginRes result = userService.login(loginReq);

        return new BaseResponse<>(result);
    }


}
