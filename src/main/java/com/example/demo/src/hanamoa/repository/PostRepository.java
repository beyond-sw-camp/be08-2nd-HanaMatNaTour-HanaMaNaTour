package com.example.demo.src.hanamoa.repository;

import com.example.demo.src.hanamoa.dto.PostSaveReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository implements PostMapper{

    private final PostMapper postMapper;

    @Override
    public int createPost(PostSaveReq params) {
        return 0;
    }

    @Override
    public PostSaveReq selectBoardDetail(Long id) {
        return null;
    }

    @Override
    public int updatePost(PostSaveReq params) {
        return 0;
    }

    @Override
    public int deletePost(Long id) {
        return 0;
    }

    @Override
    public List<PostSaveReq> selectBoardList() {
        return List.of();
    }
}
