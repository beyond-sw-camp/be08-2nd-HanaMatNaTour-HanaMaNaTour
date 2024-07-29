package com.example.demo.src.hanamoa.service;

import com.example.demo.src.hanamoa.dto.DeleteRequest;
import com.example.demo.src.hanamoa.dto.PostRequest;
import com.example.demo.src.hanamoa.dto.PostResponse;
import com.example.demo.src.hanamoa.dto.PostSearchParam;
import com.example.demo.src.hanamoa.mapper.PostMapper;
import com.example.demo.src.hanamoa.model.Post;
import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.store.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;
    private final StoreMapper storeMapper;

    // 모든 게시글을 가져오는 메소드
    public List<PostResponse> getAllPosts(int page, int size) {
        int offset = page * size;
        List<Post> posts = postMapper.getAllPosts(offset, size);
        if (posts.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NO_POSTS_FOUND);
        }
        return posts.stream()
                .map(this::mapToPostResponse) // 게시글 모델을 DTO로 변환
                .collect(Collectors.toList()); // 리스트로 반환
    }

    // 특정 ID의 게시글을 가져오는 메소드
    public PostResponse getPostById(int id) {
        Post post = postMapper.getPostById(id);
        if (post == null) {
            // 게시글이 없으면 예외 발생
            throw new BaseException(BaseResponseStatus.NO_POSTS_FOUND);
        }
        postMapper.incrementViewCount(id); // 조회수 증가
        return mapToPostResponse(post); // 게시글을 DTO로 변환하여 반환
    }

    // 새로운 게시글을 추가하는 메소드
    public void addPost(String userUuid, PostRequest postRequest) {
        if (postRequest.getTitle() == null || postRequest.getTitle().isEmpty()) {
            throw new BaseException(BaseResponseStatus.TITLE_EMPTY);
        }
        if (postRequest.getContent() == null || postRequest.getContent().isEmpty()) {
            throw new BaseException(BaseResponseStatus.CONTENT_EMPTY);
        }

        if (!storeMapper.isStoreInTable(postRequest.getStoreId())){
            throw new BaseException(BaseResponseStatus.INVALID_STORE_ID);
        }
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setUserUuid(userUuid);
        post.setStoreId(postRequest.getStoreId());
        int rowsAffected = postMapper.addPost(post); // 게시글 추가
        if (rowsAffected == 0) {
            // 오류 시 예외 발생
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ERROR);
        }
    }

    // 특정 ID의 게시글을 수정하는 메소드
    public void updatePost(int id, String userUuid, PostRequest postRequest) {
        Post post = postMapper.getPostById(id); // 요청한 id값의 게시글 model
        if (post == null) {
            throw new BaseException(BaseResponseStatus.NO_POSTS_FOUND);
        }
        // 작성자 확인
        String postUserUuid = post.getUserUuid();
        if (postUserUuid == null || !postUserUuid.equals(userUuid)) {
            throw new BaseException(BaseResponseStatus.UNAUTHORIZED);
        }
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setStoreId(postRequest.getStoreId());
        int rowsAffected = postMapper.updatePost(post); // 게시글 수정
        if (rowsAffected == 0) {
            // 수정에 실패하면 예외 발생
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ERROR);
        }
    }

    // 특정 ID의 게시글을 삭제하는 메소드
    public void deletePost(int id, String userUuid) {
        Post post = postMapper.getPostById(id);
        if (post == null) {
            throw new BaseException(BaseResponseStatus.NO_POSTS_FOUND);
        }

        if (!post.getUserUuid().equals(userUuid)){
            throw new BaseException(BaseResponseStatus.UNAUTHORIZED);
        }
        DeleteRequest request = new DeleteRequest();
        request.setId(id);
        request.setUserUuid(userUuid);
        int rowsAffected = postMapper.deletePost(request); // 게시글 삭제
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
        List<Post> posts = postMapper.searchPostsByKeyword(searchParam);
        if (posts.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NO_POSTS_FOUND);
        }
        return posts.stream()
                .map(this::mapToPostResponse) // 게시글 모델을 DTO로 변환
                .collect(Collectors.toList()); // 리스트로 반환
    }

    // 게시글 모델을 DTO로 변환하는 메소드
    private PostResponse mapToPostResponse(Post post) {
        return new PostResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getUserUuid(),
                post.getUserName(), // 작성자 이름
                post.getStoreId(),
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
                request.getStoreId() // 위치 ID
        );
    }
}
