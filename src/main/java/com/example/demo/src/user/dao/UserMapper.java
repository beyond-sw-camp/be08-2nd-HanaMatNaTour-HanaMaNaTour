package com.example.demo.src.user.dao;

import com.example.demo.src.refresh.domain.RefreshToken;
import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void save(User user);
    Optional<User> findByProvideId(@Param("userProvideId")String userProvideId);
    Optional<User> findById(@Param("userId") Long userId);
    Optional<User> findByNickname(@Param("userNickname") String userNickname);
    void deleteByNickname(@Param("userNickname")String userNickname);
    boolean isExistByNickname(@Param("userNickname")String userNickname);
    boolean isExistByEmail(@Param("userNickname")String userEmail);
    boolean isExistByRefresh(String refreshToken);
    boolean isExistByProvideId(String userProvideId);
    void deleteByRefresh(String refreshToken);
    void deleteByProvideId(String userProvideId);
    void updateNickname(@Param("userNickname")String userNickname, @Param("userProvideId")String userProvideId);
    void updateRole(@Param("role")Role role, @Param("userProvideId")String userProvideId);
}
