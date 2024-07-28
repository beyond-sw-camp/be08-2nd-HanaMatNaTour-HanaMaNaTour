//package com.example.demo.src.chat;
//
//import com.example.demo.common.exceptions.BaseException;
//import com.example.demo.src.chat.dto.LoginReq;
//import com.example.demo.src.chat.dto.LoginRes;
//import com.example.demo.src.chat.dto.SignupReq;
//import com.example.demo.src.chat.vo.User;
//import com.example.demo.src.chat.repository.UserMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import static com.example.demo.common.response.BaseResponseStatus.INVALID_LOGIN;
//import static com.example.demo.common.response.BaseResponseStatus.INVALID_SIGNUP;
//
//@RequiredArgsConstructor
//@Service
//public class UserService {
//
//    private final UserMapper userMapper;
//
//    public void signup(SignupReq signupReq){
//        int result = userMapper.insertUser(signupReq);
//        System.out.println("result : "+result);
//        if(result==0)
//            throw new BaseException(INVALID_SIGNUP);
//    }
//
//    public LoginRes login(LoginReq loginReq) {
//
//        User user  =  userMapper.selectUser(loginReq);
//        if(user==null){
//            throw new BaseException(INVALID_LOGIN);
//        }
//        return new LoginRes(user.getId(),user.getName(),user.getEmail());
//
//    }
//
//}
