package com.example.demo.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum BaseResponseStatus {

    /**
     * 200 : 요청 성공
     */
    SUCCESS(true, HttpStatus.OK.value(),"요청에 성공하였습니다."),

    /**
     * 400 : Request, Response 오류
     */

    NOT_FOUND_ERROR(false, HttpStatus.NOT_FOUND.value(), "존재하지 않는 페이지입니다."),
    NOT_FOUND_METHOD_ERROR(false, HttpStatus.NOT_FOUND.value(), "잘못된 요청입니다."),

    /* 하나모나 게시판 관련 실패 */
    NO_POSTS_FOUND(false, HttpStatus.NOT_FOUND.value(), "게시글이 존재하지 않습니다."),

    /* 음식점 관련 실패 */
    ALREADY_EXIST_STORE(false, HttpStatus.CONFLICT.value(), "중복된 가게 이름 또는 주소가 존재합니다."),
    NOT_FOUND_STORE(false, HttpStatus.NOT_FOUND.value(), "존재하지 않는 가게 입니다."),

    UNEXPECTED_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "예상치 못한 에러가 발생했습니다."),
    /**
     * 500 :  Database, Server 오류
     */
    DATABASE_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버에서 요청 처리에 실패하였습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess; // 성공 여부
        this.code = code;  // 코드
        this.message = message; // 내용
    }
}
