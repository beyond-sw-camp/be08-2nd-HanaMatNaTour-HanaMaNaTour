package com.example.demo.src.foodlist;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;


import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.foodlist.dto.ListRequestDto;

import com.example.demo.src.foodlist.dto.ListResponseDto;
import com.example.demo.src.foodlist.model.FoodList;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.example.demo.common.response.BaseResponseStatus.FOODLIST_NOT;
import static com.example.demo.common.response.BaseResponseStatus.FOOD_NOT_UPDATE;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/foodlist-service")
public class ListController {

    private final ListService listService;

    // user별 FoodList 목록 조회
    @GetMapping("/foodlists")
    @Operation
    public BaseResponse<ListResponseDto<FoodList>> getFoodListsByUser(
            @RequestParam("userId") Long userId,
            @RequestParam("page") int page,
            @RequestParam("numOfRows") int numOfRows
    ) {
        int totalCount = listService.getTotalCountByUser(userId);
        List<FoodList> foodLists = listService.getFoodListsByUser(userId, page, numOfRows);

        if (!foodLists.isEmpty()) {
            ListResponseDto<FoodList> responseDto = new ListResponseDto<>(HttpStatus.OK, foodLists, page, numOfRows, totalCount);
            return new BaseResponse<>(responseDto);
        } else {
            throw new BaseException(BaseResponseStatus.FOODLIST_NOT);
        }
    }

    // List 상세 조회 API
    @GetMapping("/foodlist/{foodlistId}")
    public BaseResponse<FoodList> getFoodListById(@PathVariable("foodlistId") Long foodlistId) {
        FoodList foodList = listService.getFoodListById(foodlistId);

        if (foodList != null) {
            return new BaseResponse<>(foodList);
        } else {
            throw new BaseException(BaseResponseStatus.FOODLIST_NOT);
        }
    }



    // List 수정 API
    // 해당하는 FoodList를 조회하고 요청 본문(ListRequestDto)에 포함된 데이터로 업데이트
    @PutMapping("/foodlists/{foodlistId}")
    public BaseResponse<List<FoodList>> updateFoodList(
            @PathVariable("foodlistId") Long foodlistId,
            @RequestBody ListRequestDto requestDto) {

        // foodlistId에 해당하는 FoodList 객체들을 조회
        List<FoodList> foodlist = listService.findFoodListById(foodlistId);



        // 조회된 FoodList가 비어있지 않은 경우에만 수정 작업 진행
        if(!foodlist.isEmpty()) {
            // 요청 받은 데이터로 FoodList 객체들을 업데이트
            for (FoodList list : foodlist) {
                list.setListRequestDto(requestDto);
                listService.updateFoodList(list);
            }

            // 업데이트된 FoodList 객체들을 다시 조회
            foodlist = listService.findFoodListById(foodlistId);

            // 업데이트된 FoodList 객체들을 BaseResponse 객체로 감싸서 반환
            return new BaseResponse<>(foodlist);
        } else {
            // FoodList가 비어있다면 예외를 던져 수정할 내용이 없다는 에러메세지를 반환
            throw new BaseException(BaseResponseStatus.FOOD_NOT_UPDATE);
        }

    }




    // List 삭제 API
    @DeleteMapping("/foodlist/{foodlistId}")
    public BaseResponse<Void> deleteFoodListById(@PathVariable("foodlistId") Long foodlistId) {
        boolean deleted = listService.deleteFoodListById(foodlistId);

        if (deleted) {
            return new BaseResponse<>(null);
        } else {
            throw new BaseException(BaseResponseStatus.FOOD_NOT_UPDATE);
        }
    }
}
