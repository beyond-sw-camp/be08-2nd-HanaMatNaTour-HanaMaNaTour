package com.example.demo.src.foodlist.dto;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ListResponseDto<FoodList> {


    @Schema(description = "페이지 번호", example = "1")
    private final int page;

    @Schema(description = "한 페이지 결과 수", example = "10")
    private final int numOfRows;

    @Schema(description = "전체 결과 수", example = "100")
    private final int totalCount;



    public ListResponseDto(HttpStatus status, List<FoodList> lists, int page, int numOfRows,int totalCount) {

        this.page = page;
        this.numOfRows = numOfRows;
        this.totalCount = totalCount;

    }

}
