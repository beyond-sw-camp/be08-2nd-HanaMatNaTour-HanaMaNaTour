package com.example.demo.src.user.service;

import com.example.demo.src.user.dao.UserMapper;
import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.dto.UserResponseDto;
import com.example.demo.src.user.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.demo.src.user.dto.UserResponseDto.*;


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

    // 회원 저장 -> 더 고민하기
    public void signUp(User user) {
        if (userMapper.isExistByNickname(user.getUserNickname())) {
            // 이미 회원인 경우 -> 메인페이지로 리다이렉트
        }
        // 신규회원인경우 -> 닉네임 설정 페이지로 리다이렉트
        userMapper.save(user);
    }

    public void setUserNickname(User user, String nickname) { // user는 토큰 공부 더 하고 수정예정
        if (isExistByNickname(nickname)) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다");
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
                .orElseThrow(() -> new NullPointerException("해당 유저가 존재하지 않습니다."));
    }

    // Nickname으로 회원찾기
    public User findByUserNickname(String userNickname) {
        return userMapper.findByNickname(userNickname)
                .orElseThrow(() -> new NullPointerException("해당 유저가 존재하지 않습니다."));
    }
    
    // Nickname으로 회원 삭제
    public void deleteByNickname(String userNickname) {
        userMapper.deleteByNickname(userNickname)
                .orElseThrow(() -> new NullPointerException("해당 유저가 존재하지 않습니다."));

    }

    // 닉네임 이미 있는지 여부 확인
    public boolean isExistByNickname(String nickname) {
        return userMapper.isExistByNickname(nickname);
    }
    // 메일 이미 있는지 여부
    public boolean isExistByEmail(String email) {
        return userMapper.isExistByEmail(email);
    }


}
