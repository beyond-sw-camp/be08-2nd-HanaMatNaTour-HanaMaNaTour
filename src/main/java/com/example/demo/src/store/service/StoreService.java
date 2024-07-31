package com.example.demo.src.store.service;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.menu.MenuServiceImpl;
import com.example.demo.src.store.dto.StoreRequest;
import com.example.demo.src.store.dto.StoreResponse;
import com.example.demo.src.store.mapper.StoreMapper;
import com.example.demo.src.store.mapper.UserStoreLikesMapper;
import com.example.demo.src.store.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreMapper storeMapper;
    private final UserStoreLikesMapper userStoreLikesMapper;
    private final MenuServiceImpl menuService;


    // 모든 음식점 가져오는 메소드
    public List<StoreResponse> getAllStores(int page, int size) {
        int offset = page * size;

        return storeMapper.getAllStores(offset, size).stream()
                .map(this::mapToStoreResponse)
                .collect(Collectors.toList());
    }

    public StoreResponse getStoreById(int id) {
        Store store = storeMapper.getStoreById(id);
        store.setMenuList(menuService.getMenusByStoreId(id));
        if(store == null) {
            // 음식점이 없으면 예외 발생
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ERROR);
        }
        return mapToStoreResponse(store);
    }

    public List<StoreResponse> getStoresByCategory(String category, int page, int size) {
        int offset = page * size;

        return storeMapper.getStoresByCategory(category, offset, size).stream()
                .map(this::mapToStoreResponse)
                .collect(Collectors.toList());
    }
    public void addStore(StoreRequest storeRequest) {

        Store store = mapToStore(storeRequest); // 요청 DTO를 모델로 변환
        if (storeMapper.isStoreExist(storeRequest.getStoreName(), storeRequest.getStoreAddress())) {
            throw new BaseException(BaseResponseStatus.ALREADY_EXIST_STORE);
        }
        int rowsAffected = storeMapper.addStore(store); // 게시글 추가
        if (rowsAffected == 0) {
            // 오류 시 예외 발생
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ERROR);
        }
    }

    public void updateStore(int id, StoreRequest storeRequest) {
        Store store = mapToStore(storeRequest);
        store.setStoreId(id);
        int rowsAffected = storeMapper.updateStore(store);
        if (rowsAffected == 0) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ERROR);
        }
    }

    public void deleteStore(int id) {
        int rowsAffected = storeMapper.deleteStore(id);
        if (rowsAffected == 0) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ERROR);
        }
    }

    // 음식점 모델을 DTO로 변환하는 메소드
    private StoreResponse mapToStoreResponse(Store store) {
    StoreResponse response = new StoreResponse();
        response.setStoreId(store.getStoreId());
        response.setStoreName(store.getStoreName());
        response.setStoreAddress(store.getStoreAddress());
        response.setCategory(store.getCategory());
        response.setLikeCount(store.getLikeCount());
        response.setAvgRating(store.getAvgRating());
        response.setUpdateAt(store.getUpdateAt());
        response.setLikeCount(userStoreLikesMapper.getLikesCount(store.getStoreId()));
        response.setMenuList(store.getMenuList());
        return response;
}

    // 요청 DTO를 음식점 모델로 변환하는 메소드
    private Store mapToStore(StoreRequest request) {
        return new Store(
                request.getStoreName(),
                request.getStoreAddress(),
                request.getCategory()
        );
    }

    public void updateStoreAverageRating(int storeId) {
        Double avgRating = storeMapper.getRatingAverage(storeId);
        if (avgRating == null) {
            avgRating = 0.0;
        }
        storeMapper.updateStoreAvgRating(storeId, avgRating);
    }
}
