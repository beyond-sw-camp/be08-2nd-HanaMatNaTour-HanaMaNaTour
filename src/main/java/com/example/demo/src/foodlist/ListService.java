package com.example.demo.src.foodlist;

import com.example.demo.src.foodlist.model.FoodList;

import com.example.demo.src.foodlist.repository.ListMapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListService {

    private final ListMapper listMapper;

    //listMapper에서 실제로 데이터를 조회한 후 그 결과 반환
    public List<FoodList> findFoodListById(Long foodlistid) {
      return listMapper.findFoodListById(foodlistid);
    }

    // FoodList 객체를 사용하여 데이터 업데이트
    public void updateFoodList(FoodList list) {
        listMapper.updateFoodList(list);
    }

    // 전체 FoodList 수를 조회
    public int getTotalCountByUser(Long userId) {
        return listMapper.getTotalCountByUser(userId);
    }

    // 특정 사용자의 페이징된 FoodList 목록을 조회
    public List<FoodList> getFoodListsByUser(Long userId, int page, int numOfRows) {
        return listMapper.getFoodListsByUser(userId, page, numOfRows);
    }

    // 특정 foodlistId에 해당하는 FoodList를 조회
    public FoodList getFoodListById(Long foodlistId) {
        return listMapper.getFoodListById(foodlistId);
    }

    // 특정 foodlistId에 해당하는 FoodList를 삭제
    public boolean deleteFoodListById(Long foodlistId) {
        return listMapper.deleteFoodListById(foodlistId);
    }
}