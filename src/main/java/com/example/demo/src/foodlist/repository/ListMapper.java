package com.example.demo.src.foodlist.repository;


import com.example.demo.src.foodlist.dto.FoodListRequest;
import com.example.demo.src.foodlist.model.FoodList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ListMapper {



    List<FoodList> getFoodListsByUser(@Param("offset") int offset, @Param("size") int size, String userName );

    FoodList getFoodListById(@Param("foodlistId") int foodlistId);

    int deleteFoodListById(@Param("foodlistId") int foodlistId);

    int updateFoodList(FoodList foodList);
}
