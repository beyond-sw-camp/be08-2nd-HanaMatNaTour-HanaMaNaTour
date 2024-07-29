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

    NO_AUTHORITY(false, HttpStatus.NOT_FOUND.value(), "해당 유저에게 권한이 없는 방입니다."),
    EMAIL_EMPTY(false,HttpStatus.BAD_REQUEST.value(), "이메일을 입력해 주세요."),
    NAME_EMPTY(false,HttpStatus.BAD_REQUEST.value(), "이름을 입력해 주세요."),
    PASSWORD_EMPTY(false,HttpStatus.BAD_REQUEST.value(), "비밀번호를 입력해 주세요."),
    EMAIL_REGEX_ERROR(false,HttpStatus.BAD_REQUEST.value(), "이메일 형식으로 입력해주세요."),


    USER_EMAIL_ALREADY_EXIST(false,HttpStatus.BAD_REQUEST.value(), "이미 가입한 이메일입니다."),

    REFRESH_TOKEN_NULL(false, HttpStatus.BAD_REQUEST.value(), "리프레시 토큰이 없습니다"),
    EXPIRED_TOKEN(false, HttpStatus.BAD_REQUEST.value(), "토큰이 만료되었습니다"),
    NOT_REFRESH_TOKEN(false, HttpStatus.BAD_REQUEST.value(), "리프레시 토큰이 아닙니다"),

    INVALID_SIGNUP(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"회원가입에 실패했습니다."),
    INVALID_LOGIN(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"아이디나 비밀번호를 다시 확인해주세요."),

    /* 하나모나 게시판 관련 실패 */
    NOT_FOUND_POSTS(false, HttpStatus.NOT_FOUND.value(), "게시글이 존재하지 않습니다."),

    /* 음식점 관련 실패 */
    ALREADY_EXIST_STORE(false, HttpStatus.CONFLICT.value(), "중복된 가게 이름 또는 주소가 존재합니다."),
    NOT_FOUND_STORE(false, HttpStatus.NOT_FOUND.value(), "존재하지 않는 가게 입니다."),


    UNEXPECTED_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "예상치 못한 에러가 발생했습니다."),
    /**
     * 500 :  Database, Server 오류
     */

    INVALID_SEND_MESSAGE(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"메세지 전송해 실패하였습니다."),

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