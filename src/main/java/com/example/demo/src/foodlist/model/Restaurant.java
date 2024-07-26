package com.example.demo.src.foodlist.model;

import com.example.demo.src.foodlist.dto.ListRequestDto;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Restaurant {

    private final Long restaurantName;

    private final String restaurantAddress;

    private final String restaurantCategory;

    private final BigDecimal score;

    public Restaurant(ListRequestDto requestDto) {

        this.score = requestDto.getScore();
        this.restaurantName = requestDto.getRestaurantName();
        this.restaurantAddress = requestDto.getRestaurantAddress();
        this.restaurantCategory = requestDto.getRestaurantCategory();
    }


}
