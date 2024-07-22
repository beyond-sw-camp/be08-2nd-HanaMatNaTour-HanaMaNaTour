package com.example.demo.src.refresh.dao;

import com.example.demo.src.refresh.domain.RefreshToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefreshTokenMapper {
    boolean isExistByRefresh(String refreshToken);
    void deleteByRefresh(String refreshToken);

    void save(RefreshToken refreshToken);
}
