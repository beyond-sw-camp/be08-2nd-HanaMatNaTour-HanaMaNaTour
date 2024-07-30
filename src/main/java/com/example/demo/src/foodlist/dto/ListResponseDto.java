package com.example.demo.src.foodlist.dto;



import lombok.*;

import java.math.BigInteger;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FoodListResponse {


//    @Schema(description = "페이지 번호", example = "1")
//    private final int page;
//
//    @Schema(description = "한 페이지 결과 수", example = "10")
//    private final int numOfRows;
//
//    @Schema(description = "전체 결과 수", example = "100")
//    private final int totalCount;
//
//
//
//    public FoodListResponse(HttpStatus status, List<FoodList> lists, int page, int numOfRows, int totalCount) {
//
//        this.page = page;
//        this.numOfRows = numOfRows;
//        this.totalCount = totalCount;
//
//    }

    private int foodlistId;

    private String foodlistName;

    private String userUuid;

    private BigInteger storeId;

    private int ListLikeCount;


}