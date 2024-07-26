package com.example.demo.src.foodlist.model;

import com.example.demo.src.foodlist.dto.ListRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


import java.math.BigInteger;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodList {

    @Schema(description = "음식리스트 아이디")
    private Long foodlistId;

    private String foodlistName;

    private String userName;

    private BigInteger like;

    private Date enrolldate;


    public void setListRequestDto(ListRequestDto requestDto) {
        // requestDto에서 필요한 값으로 설정
        this.foodlistId = requestDto.getId();
        this.foodlistName = requestDto.getFoodlistName();
        this.userName = requestDto.getUserName();
        this.like = requestDto.getLike();
        this.enrolldate = requestDto.getEnrolldate();

    }


}
