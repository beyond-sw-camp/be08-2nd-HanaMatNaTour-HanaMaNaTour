package com.example.demo.src.hanamoa;

import com.example.demo.src.hanamoa.dto.PostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    
    /** 게시글 생성 */
    public int createPost(PostDto params);

    /** 게시글 상세 조회 */
    public PostDto selectBoardDetail(Long id);

    /** 게시글 수정 */
    public int updatePost(PostDto params);

    /** 게시글 삭제 */
    public int deletePost(Long id);

    /** 게시글 목록 조회 */
    public List<PostDto> selectBoardList();

}
