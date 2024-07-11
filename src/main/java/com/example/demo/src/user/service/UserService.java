package com.example.demo.src.user.service;

import com.example.demo.src.user.dao.UserMapper;
import com.example.demo.src.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    // 회원 저장
    public void save(User user) {
        userMapper.save(user);
    }

    // ID로 회원 찾기
    public User findById(long userId) {
        return userMapper.findById(userId);
    }

    // Nickname으로 회원찾기
    public User findByUserNickname(String userNickname) {
        return userMapper.findByNickname(userNickname);
    }
    
    // Nickname으로 회원 삭제
    public void deleteByNickname(String userNickname) {
        userMapper.deleteByNickname(userNickname);
    }




}
