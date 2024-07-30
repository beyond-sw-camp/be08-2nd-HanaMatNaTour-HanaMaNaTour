package com.example.demo.src.favoriteList.service;

import com.example.demo.src.favoriteList.mapper.UserListLikesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserListLikesService {
    private final UserListLikesMapper userListLikesMapper;

    public void addLike(String userUuid, int listId) {
        if (!userListLikesMapper.isLikedByUser(userUuid, listId)) {
            userListLikesMapper.addLike(userUuid, listId);
        }
    }

    public void removeLike(String userUuid, int listId) {
        if (userListLikesMapper.isLikedByUser(userUuid, listId)) {
            userListLikesMapper.removeLike(userUuid, listId);
        }
    }

    public boolean isLikedByUser(String userUuid, int listId) {
        return userListLikesMapper.isLikedByUser(userUuid, listId);
    }
}
