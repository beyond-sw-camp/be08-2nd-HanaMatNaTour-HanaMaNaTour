package com.example.demo.src.user.service;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.src.user.dao.UserMapper;
import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.dto.SignupReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.common.response.BaseResponseStatus.USER_EMAIL_ALREADY_EXIST;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;

    public void signUp(SignupReq signupReq) {
        //  이미 가입한 이메일인지 확인
        boolean existByEmail = userMapper.isExistByEmail(signupReq.getUserEmail());
        if(existByEmail){
            throw new BaseException(USER_EMAIL_ALREADY_EXIST);
        }


        //  가입한 적이 없으면 회원가입 진행
        // todo : 비밀번호 암호화
        User newUser= signupReq.toUser(signupReq.getUserName(), signupReq.getUserEmail(), signupReq.getPassword());
        userMapper.save(newUser);
    }
}
