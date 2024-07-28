package com.example.demo.src.user.dao;

import com.example.demo.src.refresh.domain.RefreshToken;
import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void save(User user);
    Optional<User> findByProvideId(@Param("userProvideId")String userProvideId);
    boolean isExistByNickname(@Param("userNickname")String userNickname);
    boolean isExistByEmail(@Param("userEmail")String userEmail);
    boolean isExistByRefresh(String refreshToken);
    boolean isExistByProvideId(String userProvideId);
    void deleteByRefresh(String refreshToken);
    void deleteByProvideId(String userProvideId);
    void updateRole(@Param("role")Role role, @Param("userProvideId")String userProvideId);
    void updateRefreshToken(@Param("refreshToken") String refreshToken, @Param("expiration") String expiration, @Param(("userProvideId")) String userProvideId);
}
