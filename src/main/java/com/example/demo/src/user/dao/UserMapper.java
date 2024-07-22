package com.example.demo.src.user.dao;

import com.example.demo.src.refresh.domain.RefreshToken;
import com.example.demo.src.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void save(User user);
    void save(RefreshToken refreshToken);
    Optional<User> findById(@Param("userId") Long userId);
    Optional<User> findByNickname(@Param("userNickname") String userNickname);
    void deleteByNickname(@Param("userNickname")String userNickname);
    boolean isExistByNickname(@Param("userNickname")String userNickname);
    boolean isExistByEmail(@Param("userNickname")String userEmail);

    Optional<User> findByProvideId(@Param("userProvideId")String userProvideId);

}
