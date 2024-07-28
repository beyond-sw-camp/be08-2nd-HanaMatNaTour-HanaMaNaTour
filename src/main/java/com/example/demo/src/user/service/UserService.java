package com.example.demo.src.user.service;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.src.global.jwt.JWTUtil;
import com.example.demo.src.user.dao.UserMapper;
import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.dto.LoginReq;
import com.example.demo.src.user.dto.LoginResult;
import com.example.demo.src.user.dto.Provider;
import com.example.demo.src.user.dto.SignupReq;
import com.example.demo.src.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.common.response.BaseResponseStatus.INVALID_LOGIN;
import static com.example.demo.common.response.BaseResponseStatus.USER_EMAIL_ALREADY_EXIST;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;
    private final JWTUtil jwtUtil;

    public void signUp(SignupReq signupReq) {
        //  이미 가입한 이메일인지 확인
        boolean existByEmail = userMapper.isExistByEmail(signupReq.getUserEmail());
        if(existByEmail){
            throw new BaseException(USER_EMAIL_ALREADY_EXIST);
        }

        //  가입한 적이 없으면 회원가입 진행
        // todo : 비밀번호 암호화
        User newUser= signupReq.toUser(signupReq.getUserName(), signupReq.getUserEmail(), signupReq.getPassword(), Role.USER,Provider.LOCAL);
        userMapper.save(newUser);
    }

    @Transactional
    public LoginResult login(LoginReq loginReq) {

        // todo : 비밀번호 암호화


        // db 에서 유저 조회
        User user = userMapper.findByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword())
                .orElseThrow(()-> new BaseException(INVALID_LOGIN));

        // 3. 토큰 발급
        String accessToken = jwtUtil.createJwt("access",user.getUserUUId(),user.getRole(),600000L);
        String refreshToken = jwtUtil.createJwt("refresh",user.getUserUUId(),user.getRole(),86400000L);


        return LoginResult.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .name(user.getUserName())
                .email(user.getUserEmail())
                .build();

    }


}
