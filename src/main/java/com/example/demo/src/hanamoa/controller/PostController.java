package com.example.demo.src.hanamoa.controller;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.util.UserUtil;
import com.example.demo.src.hanamoa.dto.PostRequest;
import com.example.demo.src.hanamoa.dto.PostResponse;
import com.example.demo.src.hanamoa.service.PostService;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hanamoa/posts")
public class PostController {

    private final PostService postService;


    // 모든 게시글을 가져오는 API
    @GetMapping
    public BaseResponse<List<PostResponse>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<PostResponse> posts = postService.getAllPosts(page, size); // 모든 게시글 조회
            return new BaseResponse<>(posts); // 성공 응답 반환
        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.NO_POSTS_FOUND);
        }


    }

    // 특정 게시글 내용을 상세 조회하는 API
    @GetMapping("/{id}")
    public BaseResponse<PostResponse> getPostById(@PathVariable int id) {
        PostResponse post = postService.getPostById(id); // ID로 게시글 조회
        return new BaseResponse<>(post); // 성공 응답 반환
    }

    // 새로운 게시글을 추가하는 API
    @PostMapping
    public BaseResponse<String> addPost(@RequestBody PostRequest postRequest) {
        String userUUId = UserUtil.getUserUUIdFromAuthentication();
        postService.addPost(userUUId, postRequest); // 게시글 추가
        return new BaseResponse<>(BaseResponseStatus.SUCCESS); // 성공 응답 반환
    }

    // 특정 게시글을 수정하는 API
    @PutMapping("/{id}")
    public BaseResponse<String> updatePost(@PathVariable int id, @RequestBody PostRequest postRequest) {
        String userUUId = UserUtil.getUserUUIdFromAuthentication();

        postService.updatePost(id, userUUId, postRequest); // 게시글 수정
        return new BaseResponse<>(BaseResponseStatus.SUCCESS); // 성공 응답 반환
    }

    // 특정 게시글을 삭제하는 API
    @DeleteMapping("/{id}")
    public BaseResponse<String> deletePost(@PathVariable int id) {
        String userUUId = UserUtil.getUserUUIdFromAuthentication();
        postService.deletePost(id, userUUId); // 게시글 삭제
        return new BaseResponse<>(BaseResponseStatus.SUCCESS); // 성공 응답 반환
    }

    // 키워드로 게시글을 검색하는 API
    @GetMapping("/search")
    public BaseResponse<List<PostResponse>> searchPostsByKeyword(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String keyword) {
        try {
            List<PostResponse> posts = postService.searchPostsByKeyword(page, size, keyword); // 키워드로 게시글 검색
            return new BaseResponse<>(posts); // 성공 응답 반환
        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.NO_POSTS_FOUND);
        }
    }
}
