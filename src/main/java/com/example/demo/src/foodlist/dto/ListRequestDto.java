package com.example.demo.src.foodlist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Getter
@NoArgsConstructor
public class ListRequestDto {

    @Schema(description = "사용자 아이디")
    private Long id;

    @Schema(description = "음식리스트 이름")
    private String foodlistName;

    @Schema(description = "사용자 이름")
    private String userName;

    @Schema(description = "반응 수")
    private BigInteger like;

    @Schema(description = "등록 날짜")
    private Date enrolldate;


    @Schema(description = "음식점 이름")
    private Long restaurantName;

    @Schema(description = "음식점 주소")
    private String restaurantAddress;

    @Schema(description = "음식점 카테고리")
    private String restaurantCategory;

    @Schema(description = "음식점 별점")
    private BigDecimal score;


}
