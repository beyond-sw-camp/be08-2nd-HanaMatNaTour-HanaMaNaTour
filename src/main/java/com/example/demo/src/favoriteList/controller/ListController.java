package com.example.demo.src.favoriteList.controller;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.common.util.UserUtil;
import com.example.demo.src.favoriteList.dto.AddStoreRequest;
import com.example.demo.src.favoriteList.dto.FavoriteListRequest;
import com.example.demo.src.favoriteList.dto.FavoriteListResponse;
import com.example.demo.src.favoriteList.service.ListService;
import com.example.demo.src.favoriteList.service.UserListLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class ListController {

    private final ListService listService;
    private final UserListLikesService userListLikesService;

    // 맛집 리스트 생성
    @PostMapping
    public BaseResponse<String> createFavoriteList(@RequestBody FavoriteListRequest favoriteListRequest) {
        String userUuid = UserUtil.getUserUUIdFromAuthentication();
        listService.createFavoriteList(userUuid, favoriteListRequest);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    // 맛집 리스트 삭제
    @DeleteMapping("/{listId}")
    public BaseResponse<String> deleteFavoriteList(@PathVariable int listId) {
        String userUuid = UserUtil.getUserUUIdFromAuthentication();
        listService.deleteFavoriteList(userUuid, listId);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    // 맛집 리스트 전체 조회
    @GetMapping("/")
    public BaseResponse<List<FavoriteListResponse>> getFavoriteLists(@RequestParam int page, @RequestParam int size) {
        try {
            List<FavoriteListResponse> favoriteLists = listService.getFavoriteListsAll(page, size);
            return new BaseResponse<>(favoriteLists);
        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.NO_LISTS_FOUND);
        }
    }

    // 맛집 리스트에 store 추가
    @PostMapping("/{listId}")
    public BaseResponse<String> addStoreToFavoriteList(@PathVariable int listId, @RequestBody AddStoreRequest addStoreRequest) {
        String userUuid = UserUtil.getUserUUIdFromAuthentication();
        try {
            listService.addStoreToFavoriteList(userUuid, listId, addStoreRequest.getStoreId());
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    // 맛집 리스트에서 store 삭제
    @DeleteMapping("/{listId}/{storeId}")
    public BaseResponse<String> removeStoreFromFavoriteList(@PathVariable int listId, @PathVariable int storeId) {
        String userUuid = UserUtil.getUserUUIdFromAuthentication();
        try {
            listService.removeStoreFromFavoriteList(userUuid, listId, storeId);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    // 사용자 맛집 리스트 조회
    @GetMapping("/user/")
    public BaseResponse<List<FavoriteListResponse>> getUserFavoriteLists() {
        String userUUId = UserUtil.getUserUUIdFromAuthentication();

        try {
            List<FavoriteListResponse> favoriteLists = listService.getUserFavoriteLists(userUUId);
            return new BaseResponse<>(favoriteLists);
        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.NO_LISTS_FOUND);
        }
    }

    // 특정 맛집 리스트 조회
    @GetMapping("/{listId}")
    public BaseResponse<FavoriteListResponse> getFavoriteListById(@PathVariable int listId) {
        try {
            FavoriteListResponse favoriteList = listService.getFavoriteListById(listId);
            return new BaseResponse<>(favoriteList);
        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.NO_LIST_FOUND);
        }
    }

    // 해당 리스트 좋아요 상태를 변경하는 API
    @PostMapping("/{listId}/likes/toggle")
    public BaseResponse<Void> toggleLike(@PathVariable int listId) {
        String userUuid = UserUtil.getUserUUIdFromAuthentication();

        boolean isLiked = userListLikesService.isLikedByUser(userUuid, listId);
        if (isLiked) {
            userListLikesService.removeLike(userUuid, listId);
        } else {
            userListLikesService.addLike(userUuid, listId);
        }
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}