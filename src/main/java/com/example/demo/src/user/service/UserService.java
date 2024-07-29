package com.example.demo.src.user.service;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.util.SHA256;
import com.example.demo.src.global.jwt.JWTUtil;
import com.example.demo.src.user.dao.UserMapper;
import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.dto.*;
import com.example.demo.src.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.common.response.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;
    private final JWTUtil jwtUtil;

    public SignupRes signUp(SignupReq signupReq) {
        //  이미 가입한 이메일인지 확인
        boolean existByEmail = userMapper.isExistByEmail(signupReq.getUserEmail());
        if(existByEmail){
            throw new BaseException(USER_EMAIL_ALREADY_EXIST);
        }

        //  가입한 적이 없으면 회원가입 진행
        String encryptPassword = encryptPassword(signupReq.getPassword());
        User newUser= signupReq.toUser(signupReq.getUserName(), signupReq.getUserEmail(), encryptPassword, Role.USER,Provider.LOCAL);
        userMapper.save(newUser);

        return SignupRes.builder()
                .userName(newUser.getUserName())
                .userEmail(newUser.getUserEmail())
                .userUUID(newUser.getUserUUId())
                .password(newUser.getUserPassword())
                .build();


    }

    @Transactional
    public LoginResult login(LoginReq loginReq) {

        String encryptPassword = encryptPassword(loginReq.getPassword());

        // db 에서 유저 조회
        User user = userMapper.findByEmailAndPassword(loginReq.getEmail(), encryptPassword)
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


    private String encryptPassword(String password) {
        String encrypt;
        try {
            encrypt = SHA256.encrypt(password);
        } catch (Exception e) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        return encrypt;
    }


}
