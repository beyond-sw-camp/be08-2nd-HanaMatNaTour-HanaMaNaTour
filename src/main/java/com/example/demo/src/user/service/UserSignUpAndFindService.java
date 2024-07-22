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

    @Autowired
    private final UserMapper userMapper;

    /*
    회원가입관련은 CustoOAuth2UserService에 구현
    * */
    public void save(User user) {
        userMapper.save(user);
    }


    public void setUserNickname(User user, String nickname) { // user는 토큰 공부 더 하고 수정예정
        if (isExistByNickname(nickname)) {
            throw new BaseException(BaseResponseStatus.ALREADY_USE_NICKNAME);
        } else {
            user.setUserNickname(nickname);
        }
    }

    public void setUserRole(User user, Role role) {
        user.setRole(role);
    }

    // ID로 회원 찾기
    public User findById(long userId) {
        return userMapper.findById(userId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_USER));
    }


    // Nickname으로 회원찾기
    public User findByUserNickname(String userNickname) {
        return userMapper.findByNickname(userNickname)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_USER));
    }

    
    // Nickname으로 회원 삭제
    public void deleteByNickname(String userNickname) {
        if (isExistByNickname(userNickname)) throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        userMapper.deleteByNickname(userNickname);

    }

    // 닉네임 이미 있는지 여부 확인
    public boolean isExistByNickname(String nickname) {
        return userMapper.isExistByNickname(nickname);
    }
    // 메일 이미 있는지 여부
    public boolean isExistByEmail(String email) {
        return userMapper.isExistByEmail(email);
    }


    public User findByProvideId(String userProvideId) {
        return userMapper.findByProvideId(userProvideId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_USER));
    }


    // 이건 소셜로그인용
    public User checkByProvideId(String userProvideId) {
        return userMapper.findByProvideId(userProvideId).orElse(null);
    }


}
