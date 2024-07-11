package com.example.demo.src.hanamoa;

import com.example.demo.src.hanamoa.model.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    
    /** 게시글 생성 */
    public int insertBoard(BoardDto params);

    /** 게시글 상세 조회 */
    public BoardDto selectBoardDetail(Long id);

    /** 게시글 수정 */
    public int updateBoard(BoardDto params);

    /** 게시글 삭제 */
    public int deleteBoard(Long id);

    /** 게시글 목록 조회 */
    public List<BoardDto> selectBoardList();

}
