package com.example.demo.src.user.dao;

import com.example.demo.src.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void save(User user);
    Optional<User> findById(Long userId);
    Optional<User> findByNickname(String userNickName);
    Optional<User> deleteByNickname(String userNickName);
    boolean isExistByNickname(String userNickname);
    boolean isExistByEmail(String userEmail);

}
