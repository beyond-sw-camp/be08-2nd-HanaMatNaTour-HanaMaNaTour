package com.example.demo.src.item;


import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.item.dto.ItemSaveReq;
import com.example.demo.src.item.dto.ItemUpdateReq;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    // Item 저장 API
    @PostMapping("")
    public BaseResponse<String> saveItem(@RequestBody ItemSaveReq item) {
        itemService.saveItem(item);
        return new BaseResponse<>("아이템 저장을 완료했습니다.");
    }

    // Item 수정 API
    @PatchMapping("")
    public String updateItem(@RequestBody ItemUpdateReq item) {
        itemService.updateItem(item);
        return "수정 완료";
    }

//    @GetMapping()
//    public Strin




}
