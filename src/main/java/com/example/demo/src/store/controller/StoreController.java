package com.example.demo.src.store.controller;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.common.util.UserUtil;
import com.example.demo.src.store.dto.StoreRequest;
import com.example.demo.src.store.dto.StoreResponse;
import com.example.demo.src.store.service.StoreService;
import com.example.demo.src.store.service.UserStoreLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hanamoa/store")
public class StoreController {

    private final StoreService storeService;
    private final UserStoreLikesService userStoreLikesService;


    // 모든 음식점 정보를 가져오는 API
    @GetMapping
    public BaseResponse<List<StoreResponse>> getAllStores(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<StoreResponse> stores = storeService.getAllStores(page, size); // 모든 게시글 조회
            return new BaseResponse<>(stores); // 성공 응답 반환
        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.NOT_FOUND_STORE);
        }
    }

    // 특정 음식점 정보 상세 조회하는 API
    @GetMapping("/{id}")
    public BaseResponse<StoreResponse> getStoreById(@PathVariable int id) {
        StoreResponse store = storeService.getStoreById(id); // ID로 특정 음식점 조회
        store.setLikeCount(userStoreLikesService.getLikesCount(id));
        return new BaseResponse<>(store); // 성공 응답 반환
    }

    // 카테고리로 음식점 리스트 조회하는 API
    @GetMapping("/category")
    public BaseResponse<List<StoreResponse>> getStoresByCategory(
            @RequestParam String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        try {
            List<StoreResponse> stores = storeService.getStoresByCategory(category, page, size); // 카테고리별 음식점 조회
            return new BaseResponse<>(stores); // 성공 응답 반환
        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.NOT_FOUND_STORE);
        }
    }

    // 음식점 등록하는 API
    @PostMapping
    public BaseResponse<String> addStore(@RequestBody StoreRequest storeRequest) {
        try {
            storeService.addStore(storeRequest); // 게시글 추가
            return new BaseResponse<>(BaseResponseStatus.SUCCESS); // 성공 응답 반환
        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.ALREADY_EXIST_STORE); // 중복 예외 반환
        }
    }

    // 음식점 정보를 수정하는 API
    @PutMapping("/{id}")
    public BaseResponse<String> updateStore(@PathVariable int id, @RequestBody StoreRequest storeRequest) {
        storeService.updateStore(id, storeRequest); // 음식점 수정
        return new BaseResponse<>(BaseResponseStatus.SUCCESS); // 성공 응답 반환
    }

    // 음식점 정보를 삭제하는 API
    @DeleteMapping("/{id}")
    public BaseResponse<String> deleteStore(@PathVariable int id) {
        storeService.deleteStore(id); // 음식점 삭제
        return new BaseResponse<>(BaseResponseStatus.SUCCESS); // 성공 응답 반환
    }


    // 좋아요 상태를 변경하는 API
    @PostMapping("/likes/toggle")
    public BaseResponse<Void> toggleLike(@RequestParam String userProvideId, @RequestParam int storeId) {
        boolean isLiked = userStoreLikesService.isLikedByUser(userProvideId, storeId);
        if (isLiked) {
            userStoreLikesService.removeLike(userProvideId, storeId);
        } else {
            userStoreLikesService.addLike(userProvideId, storeId);
        }
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    // 유저가 특정 음식점을 좋아하는지 확인하는 API
    @GetMapping("/likes/is-liked")
    public BaseResponse<Boolean> isLikedByUser(@RequestParam int storeId) {
        String userUUId = UserUtil.getUserUUIdFromAuthentication();
        boolean isLiked = userStoreLikesService.isLikedByUser(userUUId, storeId);
        return new BaseResponse<>(isLiked);
    }
}
