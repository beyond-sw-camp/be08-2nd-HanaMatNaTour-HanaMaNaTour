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
    ALREADY_USE_NICKNAME(false, HttpStatus.ALREADY_REPORTED.value(), "이미 존재하는 닉네임입니다."),
    NOT_FOUND_USER(false, HttpStatus.NOT_FOUND.value(), "해당 유저가 존재하지 않습니다."),


    REFRESH_TOKEN_NULL(false, HttpStatus.BAD_REQUEST.value(), "리프레시 토큰이 없습니다"),
    EXPIRED_TOKEN(false, HttpStatus.BAD_REQUEST.value(), "토큰이 만료되었습니다"),
    NOT_REFRESH_TOKEN(false, HttpStatus.BAD_REQUEST.value(), "리프레시 토큰이 아닙니다"),


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