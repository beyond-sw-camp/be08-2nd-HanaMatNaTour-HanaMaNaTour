package com.example.demo.common.exceptions;

import com.example.demo.common.response.BaseResponse;
import com.example.demo.common.response.BaseResponseStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BaseException.class)
    public BaseResponse<BaseResponseStatus> baseExceptionHandle(BaseException exception) {
        log.warn("BaseException. error message: {}", exception.getMessage());
        return new BaseResponse<>(exception.getStatus());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse<BaseResponseStatus> notFoundExceptionHandle(NoHandlerFoundException e, HttpServletRequest request){
        return new BaseResponse<>(BaseResponseStatus.NOT_FOUND_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse<BaseResponseStatus> notFoundRequestMethodExceptionHandle(HttpRequestMethodNotSupportedException e, HttpServletRequest request){
        return new BaseResponse<>(BaseResponseStatus.NOT_FOUND_METHOD_ERROR);
    }


    @ExceptionHandler(Exception.class)
    public BaseResponse<BaseResponseStatus> exceptionHandle(Exception exception) {
        log.error("Exception has occured. ", exception);
        return new BaseResponse<>(BaseResponseStatus.UNEXPECTED_ERROR);
    }
}
