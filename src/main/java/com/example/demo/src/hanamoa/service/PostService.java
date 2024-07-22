package com.example.demo.src.hanamoa.service;

import com.example.demo.src.hanamoa.dto.PostRequest;
import com.example.demo.src.hanamoa.dto.PostResponse;
import com.example.demo.src.hanamoa.mapper.PostMapper;
import com.example.demo.src.hanamoa.model.Post;
import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    // 모든 게시글을 가져오는 메소드
    public List<PostResponse> getAllPosts() {
        return postMapper.getAllPosts().stream()
                .map(this::mapToPostResponse) // 게시글 모델을 DTO로 변환
                .collect(Collectors.toList()); // 리스트로 반환
    }

    // 특정 ID의 게시글을 가져오는 메소드
    public PostResponse getPostById(int id) {
        Post post = postMapper.getPostById(id);
        if (post == null) {
            // 게시글이 없으면 예외 발생
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ERROR);
        }
        postMapper.incrementViewCount(id); // 조회수 증가
        return mapToPostResponse(post); // 게시글을 DTO로 변환하여 반환
    }

    // 새로운 게시글을 추가하는 메소드
    public void addPost(PostRequest postRequest) {
        Post post = mapToPost(postRequest); // 요청 DTO를 모델로 변환
        postMapper.addPost(post); // 게시글 추가
    }

    // 특정 ID의 게시글을 수정하는 메소드
    public void updatePost(int id, PostRequest postRequest) {
        Post post = mapToPost(postRequest); // 요청 DTO를 모델로 변환
        post.setId(id); // ID 설정
        int rowsAffected = postMapper.updatePost(post); // 게시글 수정
        if (rowsAffected == 0) {
            // 수정에 실패하면 예외 발생
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ERROR);
        }
    }

    // 특정 ID의 게시글을 삭제하는 메소드
    public void deletePost(int id) {
        int rowsAffected = postMapper.deletePost(id); // 게시글 삭제
        if (rowsAffected == 0) {
            // 삭제에 실패하면 예외 발생
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ERROR);
        }
    }

    // 키워드로 게시글을 검색하는 메소드
    public List<PostResponse> searchPostsByKeyword(String keyword) {
        return postMapper.searchPostsByKeyword(keyword).stream()
                .map(this::mapToPostResponse) // 게시글 모델을 DTO로 변환
                .collect(Collectors.toList()); // 리스트로 반환
    }

    // 게시글 모델을 DTO로 변환하는 메소드
    private PostResponse mapToPostResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUserName(), // 작성자 이름
                post.getLocationName(), // 위치 이름
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getViewCount() // 조회수
        );
    }

    // 요청 DTO를 게시글 모델로 변환하는 메소드
    private Post mapToPost(PostRequest request) {
        return new Post(
                request.getTitle(),
                request.getContent(),
                request.getUserId(), // 작성자 ID
                request.getLocationId() // 위치 ID
        );
    }
}
