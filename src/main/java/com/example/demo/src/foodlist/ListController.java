package com.example.demo.src.foodlist;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;


import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.foodlist.dto.FoodListRequest;

import com.example.demo.src.foodlist.dto.FoodListResponse;
import com.example.demo.src.foodlist.model.FoodList;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/foodlist")
public class ListController {

    private final ListService listService;

    // user별 FoodList 목록 조회
    @GetMapping("/foodlists")
    @Operation
    public BaseResponse<List<FoodListResponse>> getFoodListsByUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam String userName
    ) {
        // 모든 FoodList 조회

        List<FoodListResponse> foodLists = listService.getFoodListsByUser(page, size, userName);

        if (!foodLists.isEmpty()) {
            return new BaseResponse<>(foodLists);
        } else {
            throw new BaseException(BaseResponseStatus.FOODLIST_NOT);
        }
    }

    // List 상세 조회 API
    @GetMapping("/foodlist/{foodlistId}")
    public BaseResponse<FoodList> getFoodListById(@PathVariable("foodlistId") int foodlistId) {
        FoodList foodLists = listService.getFoodListById(foodlistId);

        if (foodLists != null) {
            return new BaseResponse<>(foodLists);
        } else {
            throw new BaseException(BaseResponseStatus.FOODLIST_NOT);
        }
    }



    // List 수정 API
    // 해당하는 FoodList를 조회하고 요청 본문(ListRequestDto)에 포함된 데이터로 업데이트
    @PutMapping("/foodlists/{foodlistId}")
    public BaseResponse<List<FoodList>> updateFoodList(
            @PathVariable("foodlistId") int foodlistId,
            @RequestBody FoodListRequest requestDto) {

        // foodlistId에 해당하는 FoodList 객체들을 조회
//        List<FoodList> foodlist = listService.findFoodListById(foodlistId);
        // 리스트 수정
        listService.updateFoodList(foodlistId, requestDto.getFoodlistName());

        // 성공 시 반환
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);


    }



    // List 삭제 API
    @DeleteMapping("/foodlist/{foodlistId}")
    public BaseResponse<String> deleteFoodListById(@PathVariable("foodlistId") int foodlistId) {
        listService.deleteFoodListById(foodlistId);

        throw new BaseException(BaseResponseStatus.SUCCESS);

    }
}

