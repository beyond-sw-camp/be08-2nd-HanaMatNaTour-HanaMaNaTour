package com.example.demo.src.store.controller;

import com.example.demo.common.response.BaseResponse;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.hanamoa.dto.PostRequest;
import com.example.demo.src.hanamoa.dto.PostResponse;
import com.example.demo.src.store.dto.StoreRequest;
import com.example.demo.src.store.dto.StoreResponse;
import com.example.demo.src.store.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hanamoa/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    // 모든 음식점 정보를 가져오는 API
    @GetMapping
    public BaseResponse<List<StoreResponse>> getAllStores() {
        List<StoreResponse> stores = storeService.getAllStores(); // 모든 게시글 조회
        return new BaseResponse<>(stores); // 성공 응답 반환
    }

    // 특정 음식점을 ID로 조회하는 API
    @GetMapping("/{id}")
    public BaseResponse<StoreResponse> getStoreById(@PathVariable int id) {
        StoreResponse store = storeService.getStoreById(id); // ID로 게시글 조회
        return new BaseResponse<>(store); // 성공 응답 반환
    }

    // 음식점 등록하는 API
    @PostMapping
    public BaseResponse<String> addStore(@RequestBody StoreRequest storeRequest) {
        storeService.addStore(storeRequest); // 게시글 추가
        return new BaseResponse<>(BaseResponseStatus.SUCCESS); // 성공 응답 반환
    }

    // 음식점 정보를 수정하는 API
    @PutMapping("/{id}")
    public BaseResponse<String> updateStore(@PathVariable Long id, @RequestBody StoreRequest storeRequest) {
        storeService.updateStore(id, storeRequest); // 게시글 수정
        return new BaseResponse<>(BaseResponseStatus.SUCCESS); // 성공 응답 반환
    }

    // 음식점 정보를 삭제하는 API
    @DeleteMapping("/{id}")
    public BaseResponse<String> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id); // 게시글 삭제
        return new BaseResponse<>(BaseResponseStatus.SUCCESS); // 성공 응답 반환
    }



}
