package com.example.demo.src.favoriteList.service;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.favoriteList.dto.FavoriteListRequest;
import com.example.demo.src.favoriteList.dto.FavoriteListResponse;
import com.example.demo.src.favoriteList.mapper.ListMapper;
import com.example.demo.src.favoriteList.mapper.UserListLikesMapper;
import com.example.demo.src.store.mapper.StoreMapper;
import com.example.demo.src.store.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListService {

    private final ListMapper listMapper;
    private final StoreMapper storeMapper;
    private final UserListLikesMapper userListLikesMapper;

    // 맛집 리스트 전체 조회
    public List<FavoriteListResponse> getFavoriteListsAll(int page, int size){
        int offset = page * size;

        List<FavoriteListResponse> responses = listMapper.getFavoriteListsAll(offset, size);

        // 각 FavoriteListResponse의 storeIds를 List<Store>로 변환
        return getFavoriteListResponses(responses);
    }

    // 맛집 리스트 생성
    public void createFavoriteList(String userUuid, FavoriteListRequest favoriteListRequest) {
        listMapper.createFavoriteList(userUuid, favoriteListRequest);
    }

    // 맛집 리스트 삭제
    public void deleteFavoriteList(String userUuid, int listId) {
        listMapper.deleteFavoriteList(userUuid, listId);
    }

    // 맛집 리스트에 store 추가
    public void addStoreToFavoriteList(String userUuid, int listId, int storeId) throws BaseException {
        verifyUserPermission(userUuid, listId);
        listMapper.addStoreToFavoriteList(listId, storeId);
    }

    // 맛집 리스트에서 store 삭제
    public void removeStoreFromFavoriteList(String userUuid, int listId, int storeId) throws BaseException {
        verifyUserPermission(userUuid, listId);
        listMapper.removeStoreFromFavoriteList(listId, storeId);
    }

    // 사용자 맛집 리스트 조회
    public List<FavoriteListResponse> getUserFavoriteLists(String userUuid) throws BaseException {
        List<FavoriteListResponse> responses = listMapper.getUserFavoriteLists(userUuid);

        // 각 FavoriteListResponse의 storeIds를 List<Store>로 변환
        return getFavoriteListResponses(responses);

    }

    // 다른 사용자 맛집 리스트 조회
    public FavoriteListResponse getFavoriteListById(int listId) throws BaseException {
        return getFavoriteListInfoById(listId);
    }

    // 사용자 권한 확인
    private void verifyUserPermission(String userUuid, int listId) throws BaseException {
        String ownerUuid = listMapper.getOwnerByListId(listId);
        if (!userUuid.equals(ownerUuid)) {
            throw new BaseException(BaseResponseStatus.INVALID_USER_JWT);
        }
    }

    // FavoriteListResponse의 storeIds값을 받아와서 store에 있는 정보를 List형태로 다시 저장
    private List<FavoriteListResponse> getFavoriteListResponses(List<FavoriteListResponse> responses) {
        for (FavoriteListResponse response : responses) {

            // storeIds가 null인 경우 빈 리스트로 처리
            if (response.getStoreIds() == null || response.getStoreIds().trim().isEmpty()) {
                response.setStores(Collections.emptyList());
            } else {

                // storeIds를 쉼표로 분리하여 Integer 리스트로 변환
                String[] storeIdsArray = response.getStoreIds().split(",");
                List<Integer> storeIds = Arrays.stream(storeIdsArray)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                // storeIds로 Store 객체 리스트 조회
                List<Store> stores = storeMapper.getStoresByIds(storeIds);
                response.setStores(stores);
            }
        }
        return responses;
    }

    private FavoriteListResponse getFavoriteListInfoById(int listId){
        FavoriteListResponse response =  listMapper.getFavoriteListById(listId);
        String[] storeIdsArray = response.getStoreIds().split(",");
        List<Integer> storeIds = Arrays.stream(storeIdsArray).
                map(Integer::parseInt).
                collect(Collectors.toList());
        List<Store> stores = storeMapper.getStoresByIds(storeIds);
        response.setStores(stores);
        response.setLikeCount(userListLikesMapper.getLikesCount(response.getListId()));

        return response;
    }

}
