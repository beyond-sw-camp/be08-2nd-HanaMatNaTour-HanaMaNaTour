package com.example.demo.src.store.service;

import com.example.demo.src.store.mapper.UserStoreLikesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserStoreLikesService {

    private final UserStoreLikesMapper userStoreLikesMapper;


    public void addLike(String userId, int storeId) {
        if (!userStoreLikesMapper.isLikedByUser(userId, storeId)) {
            userStoreLikesMapper.addLike(userId, storeId);
        }
    }

    public void removeLike(String userId, int storeId) {
        if (userStoreLikesMapper.isLikedByUser(userId, storeId)) {
            userStoreLikesMapper.removeLike(userId, storeId);
        }
    }

    public int getLikesCount(int storeId) {
        return userStoreLikesMapper.getLikesCount(storeId);
    }

    public boolean isLikedByUser(String userId, int storeId) {
        return userStoreLikesMapper.isLikedByUser(userId, storeId);
    }
}
