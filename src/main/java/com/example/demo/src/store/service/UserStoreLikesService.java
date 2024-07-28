package com.example.demo.src.store.service;

import com.example.demo.src.store.mapper.UserStoreLikesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserStoreLikesService {

    private final UserStoreLikesMapper userStoreLikesMapper;


    public void addLike(String userProvideId, int storeId) {
        if (!userStoreLikesMapper.isLikedByUser(userProvideId, storeId)) {
            userStoreLikesMapper.addLike(userProvideId, storeId);
        }
    }

    public void removeLike(String userProvideId, int storeId) {
        if (userStoreLikesMapper.isLikedByUser(userProvideId, storeId)) {
            userStoreLikesMapper.removeLike(userProvideId, storeId);
        }
    }

    public int getLikesCount(int storeId) {
        return userStoreLikesMapper.getLikesCount(storeId);
    }

    public boolean isLikedByUser(String userProvideId, int storeId) {
        return userStoreLikesMapper.isLikedByUser(userProvideId, storeId);
    }
}
