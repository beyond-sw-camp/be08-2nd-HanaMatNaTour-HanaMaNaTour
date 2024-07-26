package com.example.demo.src.foodlist.repository;


import com.example.demo.src.foodlist.dto.ListRequestDto;
import com.example.demo.src.foodlist.model.FoodList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ListMapper {

    List<FoodList> findFoodListById(Long foodlistid);

    int getTotalCountByUser(@Param("userId") Long userId);

    List<FoodList> getFoodListsByUser(@Param("userId") Long userId, @Param("offset") int page, @Param("limit") int numOfRows);

    FoodList getFoodListById(@Param("foodlistId") Long foodlistId);

    boolean deleteFoodListById(@Param("foodlistId") Long foodlistId);

    void updateFoodList(@Param("list") FoodList list);
}
