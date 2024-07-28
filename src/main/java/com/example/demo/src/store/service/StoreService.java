package com.example.demo.src.store.service;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.hanamoa.model.Post;
import com.example.demo.src.store.dto.StoreRequest;
import com.example.demo.src.store.dto.StoreResponse;
import com.example.demo.src.store.mapper.StoreMapper;
import com.example.demo.src.store.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreMapper storeMapper;


    // 모든 음식점 가져오는 메소드
    public List<StoreResponse> getAllStores() {
        return storeMapper.getAllStores().stream()
                .map(this::mapToStoreResponse)
                .collect(Collectors.toList());
    }

    public StoreResponse getStoreById(int id) {
        Store store = storeMapper.getStoreById(id);
        if(store == null) {
            // 음식점이 없으면 예외 발생
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ERROR);
        }
        return mapToStoreResponse(store);
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
        storeMapper.addStore(store);
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
        return new StoreResponse(
                store.getStoreId(),
                store.getStoreName(),
                store.getStoreAddress(),
                store.getCategory(),
                store.getLikeCount(),
                store.getCreateAt()
        );
    }

    // 요청 DTO를 음식점 모델로 변환하는 메소드
    private Store mapToStore(StoreRequest request) {
        return new Store(
                request.getStoreName(),
                request.getStoreAddress(),
                request.getCategory()
        );
    }

    public List<StoreResponse> getStoresByCategory(String category) {
        return storeMapper.getStoresByCategory(category);
    }
}
