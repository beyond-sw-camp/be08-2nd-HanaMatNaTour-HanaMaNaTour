package com.example.demo.src.user.dao;

import com.example.demo.src.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void save(User user);
    User findById(Long id);
    User findByNickname(String nickName);
    void deleteByNickname(String nickName);
}
