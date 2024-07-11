package com.example.demo.src.hanamoa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardDto {

    /** 번호 (PK) */
    private Long id;

    /** 제목 */
    private String title;

    /** 내용 */
    private String content;

    /** 작성자 */
    private String writer;

    /** 조회 수 */
    private int viewCnt;

    /** 식당 이름 */
    private String restaurantName;

    /** 참가신청인원 */
    private int recruitingMember;

    /** 인원 수 */
    private int headCount;

    /** 등록일시 */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm")
    private LocalDateTime createdAt;

    /** 수정일시 */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm")
    private LocalDateTime updateAt;



}
