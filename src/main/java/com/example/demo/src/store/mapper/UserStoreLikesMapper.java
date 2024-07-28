package com.example.demo.src.store.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserStoreLikesMapper {
    // 좋아요 추가
    int addLike(@Param("userProvideId") String userProvideId, @Param("storeId") int storeId);

    // 좋아요 삭제
    int removeLike(@Param("userProvideId") String userProvideId, @Param("storeId") int storeId);

    // 특정 가게에 대한 좋아요 수 가져오기
    int getLikesCount(@Param("storeId") int storeId);

    // 사용자가 특정 가게를 좋아요 눌렀는지 확인
    boolean isLikedByUser(@Param("userProvideId") String userProvideId, @Param("storeId") int storeId);
}

