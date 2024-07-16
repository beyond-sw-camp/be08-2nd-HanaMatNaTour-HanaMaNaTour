package com.example.demo.src.user.dao;

import com.example.demo.src.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void save(User user);
    Optional<User> findById(@Param("userId") Long userId);
    Optional<User> findByNickname(@Param("userNickname") String userNickname);
    Optional<User> deleteByNickname(@Param("userNickname")String userNickname);
    boolean isExistByNickname(@Param("userNickname")String userNickname);
    boolean isExistByEmail(@Param("userNickname")String userEmail);

}
