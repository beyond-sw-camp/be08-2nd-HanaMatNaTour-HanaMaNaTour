package com.example.demo.src.user.repository;

import com.example.demo.src.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    void save(User user);
    User findById(Long id);
    User findByUserNickname(String nickName);
    void deleteByNickname(String nickName);
    void init();


    /*
    이후 개발

    - 어떤 식당의 리뷰를 작성한 사람들 리스트
    - 어떤 하나모아에 들어간 사람들 리스트
    - 어떤 리스트를 좋아한 사람들 리스트

    */



}
