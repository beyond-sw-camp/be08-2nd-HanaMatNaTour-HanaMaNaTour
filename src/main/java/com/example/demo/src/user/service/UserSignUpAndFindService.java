package com.example.demo.src.user.service;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.refresh.domain.RefreshToken;
import com.example.demo.src.user.dao.UserMapper;
import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
* # 기존 내 생각 userRepo 안에 구현했던 서비스들
*   - 유저 저장
*   - 유저 조회 (idx, nickname)
*   - 유저 삭제
* */
@Service
@AllArgsConstructor
public class UserSignUpAndFindService {

    private final UserMapper userMapper;

    /*
    회원가입관련은 CustoOAuth2UserService에 구현
    * */
    public void save(User user) {
        //todo : provider 에 소셜 로그인별 enum 값 저장
        System.out.println("-----UserSignUpAndFindService------");
        System.out.println(user);
        userMapper.save(user);
    }


    public void setUserRole(User user, Role role) {
        user.setRole(role);
    }


    // 닉네임 이미 있는지 여부 확인
    public boolean isExistByNickname(String nickname) {
        return userMapper.isExistByNickname(nickname);
    }
    // 메일 이미 있는지 여부
    public boolean isExistByEmail(String email) {
        return userMapper.isExistByEmail(email);
    }

    public User findById(Long userId) {
        return userMapper.findById(userId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_USER));
    }

    public User findByProvideId(String userProvideId) {
        return userMapper.findByProvideId(userProvideId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_USER));
    }


    public User findByUUID(String userUUId) {
        return userMapper.findByUserUUID(userUUId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_USER));
    }

    // 이건 소셜로그인용
    public User checkByProvideId(String userProvideId) {
        return userMapper.findByProvideId(userProvideId).orElse(null);
    }

    public boolean isExistByRefresh(String refreshToken) {
        return userMapper.isExistByRefresh(refreshToken);
    }

    public boolean isExistByProvideId(String userProvideId) {
        return userMapper.isExistByProvideId(userProvideId);
    }

    public void deleteByRefresh(String refreshToken) {
        if (!isExistByRefresh(refreshToken)) throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        userMapper.deleteByRefresh(refreshToken);
    }

    public void deleteByProvideId(String userProvideId) {
        if (!isExistByProvideId(userProvideId)) throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        userMapper.deleteByProvideId(userProvideId);
    }


    public void updateRole(Role role, String userProvideId) {
        userMapper.updateRole(role, userProvideId);
    }

    public void updateRefreshToken(String refresh, String expiration, String userProvideId) {
        userMapper.updateRefreshToken(refresh, expiration, userProvideId);
    }


}
