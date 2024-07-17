package com.example.demo.src.user.dao;

import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.dto.UserResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

import static com.example.demo.src.user.dto.UserResponseDto.*;

@Mapper
public interface UserMapper {
    void save(User user);
    Optional<User> findByProvideId(@Param("userProvideId") String userProvideId);
    Optional<User> findById(@Param("userId") Long userId);
    Optional<User> findByNickname(@Param("userNickname") String userNickname);
    Optional<User> deleteByNickname(@Param("userNickname")String userNickname);
    boolean isExistByNickname(@Param("userNickname")String userNickname);
    boolean isExistByEmail(@Param("userNickname")String userEmail);

}
