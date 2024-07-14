package com.example.demo.src.hanamoa.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {

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
    private LocalDateTime createdAt;

    /** 수정일시 */
    private LocalDateTime updateAt;



}
