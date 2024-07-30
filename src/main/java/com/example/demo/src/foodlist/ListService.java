package com.example.demo.src.foodlist;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.foodlist.dto.FoodListRequest;
import com.example.demo.src.foodlist.dto.FoodListResponse;
import com.example.demo.src.foodlist.model.FoodList;
import com.example.demo.src.foodlist.repository.ListMapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ListService {

    private final ListMapper listMapper;

    //listMapper에서 실제로 데이터를 조회한 후 그 결과 반환
//    public List<FoodList> findFoodListById(int foodlistid) {
//      return listMapper.findFoodListById(foodlistid);
//    }

    // FoodList 객체를 사용하여 데이터 업데이트
    public void updateFoodList(int foodlistId, String foodlistName) {
        FoodList foodList = listMapper.getFoodListById(foodlistId);
        foodList.setFoodlistName(foodlistName);
        listMapper.updateFoodList(foodList);
    }

//    private FoodList mapToList(String foodlistName) {
//        return new FoodList(
//                foodlistName.getFoodlistName()
//        );
//    }

//    // 전체 FoodList 수를 조회
//    public int getTotalCountByUser(Long userId) {
//        return listMapper.getTotalCountByUser(userId);
//    }

    // 특정 사용자의 페이징된 FoodList 목록을 조회
    public List<FoodListResponse> getFoodListsByUser(int page, int size, String userName) {

        int offset = page * size; // 페이지와 사이즈를 기반으로 오프셋 계산

        return listMapper.getFoodListsByUser(offset, size, userName).stream()
                .map(this::mapToListResponse)
                .collect(Collectors.toList()); // 리스트로 수집
    }

    private FoodListResponse mapToListResponse(FoodList foodList) {
        FoodListResponse response = new FoodListResponse();
        response.setFoodlistId(foodList.getFoodlistId());
        response.setFoodlistName(foodList.getFoodlistName());
        response.setListLikeCount(foodList.getListLikeCount());

        return response;
    }

    // 특정 foodlistId에 해당하는 FoodList를 조회
    public FoodList getFoodListById(int foodlistId) {
        return listMapper.getFoodListById(foodlistId);
    }

    // 특정 foodlistId에 해당하는 FoodList를 삭제
    public void deleteFoodListById(int foodlistId) {
        int rowsAffected = listMapper.deleteFoodListById(foodlistId);
        if (rowsAffected == 0) {
            throw new BaseException(BaseResponseStatus.FOODLIST_NOT);
        }
    }

}