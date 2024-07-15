package com.example.demo.src.hanamoa.repository;

import com.example.demo.src.hanamoa.dto.PostSaveReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    
    /** 게시글 생성 */
    public int createPost(PostSaveReq params);

    /** 게시글 상세 조회 */
    public PostSaveReq selectBoardDetail(Long id);

    /** 게시글 수정 */
    public int updatePost(PostSaveReq params);

    /** 게시글 삭제 */
    public int deletePost(Long id);

    /** 게시글 목록 조회 */
    public List<PostSaveReq> selectBoardList();

}
