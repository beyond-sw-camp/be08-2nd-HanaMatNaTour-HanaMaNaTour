package com.example.demo.src.store.service;

import com.example.demo.src.store.mapper.UserStoreLikesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserStoreLikesService {

    private final UserStoreLikesMapper userStoreLikesMapper;


    public void addLike(String userUuid, int storeId) {
        if (!userStoreLikesMapper.isLikedByUser(userUuid, storeId)) {
            userStoreLikesMapper.addLike(userUuid, storeId);
        }
    }

    public void removeLike(String userUuid, int storeId) {
        if (userStoreLikesMapper.isLikedByUser(userUuid, storeId)) {
            userStoreLikesMapper.removeLike(userUuid, storeId);
        }
    }

    public int getLikesCount(int storeId) {
        return userStoreLikesMapper.getLikesCount(storeId);
    }

    public boolean isLikedByUser(String userUuid, int storeId) {
        return userStoreLikesMapper.isLikedByUser(userUuid, storeId);
    }
}
