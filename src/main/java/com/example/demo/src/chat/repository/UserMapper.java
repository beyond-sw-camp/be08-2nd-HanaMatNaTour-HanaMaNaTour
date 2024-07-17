package com.example.demo.src.chat.repository;

import com.example.demo.src.chat.dto.LoginReq;
import com.example.demo.src.chat.dto.SignupReq;
import com.example.demo.src.chat.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    int insertUser(@Param("user") SignupReq signupReq);

    User selectUser(LoginReq loginReq);
}
