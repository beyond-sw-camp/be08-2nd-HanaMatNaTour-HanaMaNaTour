package com.example.demo.src.hanamoa.service;

import com.example.demo.src.hanamoa.dto.PostRequest;
import com.example.demo.src.hanamoa.dto.PostResponse;
import com.example.demo.src.hanamoa.dto.PostSearchParam;
import com.example.demo.src.hanamoa.mapper.PostMapper;
import com.example.demo.src.hanamoa.model.Post;
import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    // 모든 게시글을 가져오는 메소드
    public List<PostResponse> getAllPosts(int page, int size) {
        int offset = page * size;
        return postMapper.getAllPosts(offset, size).stream()
                .map(this::mapToPostResponse) // 게시글 모델을 DTO로 변환
                .collect(Collectors.toList()); // 리스트로 반환
    }

    // 특정 ID의 게시글을 가져오는 메소드
    public PostResponse getPostById(int id) {
        Post post = postMapper.getPostById(id);
        if (post == null) {
            // 게시글이 없으면 예외 발생
            throw new BaseException(BaseResponseStatus.NOT_FOUND_POSTS);
        }
        postMapper.incrementViewCount(id); // 조회수 증가
        return mapToPostResponse(post); // 게시글을 DTO로 변환하여 반환
    }

    // 새로운 게시글을 추가하는 메소드
    public void addPost(PostRequest postRequest) {
        Post post = mapToPost(postRequest); // 요청 DTO를 모델로 변환
        int rowsAffected = postMapper.addPost(post); // 게시글 추가
        if (rowsAffected == 0) {
            // 오류 시 예외 발생
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ERROR);
        }
    }

    // 특정 ID의 게시글을 수정하는 메소드
    public void updatePost(int id, PostRequest postRequest) {
        Post post = mapToPost(postRequest); // 요청 DTO를 모델로 변환
        post.setPostId(id); // ID 설정
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
    public List<PostResponse> searchPostsByKeyword(int page, int size, String keyword) {
        PostSearchParam searchParam = new PostSearchParam();
        int offset = page * size;
        searchParam.setKeyword(keyword);
        searchParam.setOffset(offset);
        searchParam.setSize(size);
        return postMapper.searchPostsByKeyword(searchParam).stream()
                .map(this::mapToPostResponse) // 게시글 모델을 DTO로 변환
                .collect(Collectors.toList()); // 리스트로 반환
    }

    // 게시글 모델을 DTO로 변환하는 메소드
    private PostResponse mapToPostResponse(Post post) {
        return new PostResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getUserName(), // 작성자 이름
                post.getStoreName(), // 위치 이름
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
                request.getUserProvideId(), // 작성자 ID
                request.getStoreId() // 위치 ID
        );
    }
}
