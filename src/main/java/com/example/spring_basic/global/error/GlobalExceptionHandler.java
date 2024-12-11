package com.example.spring_basic.global.error;

import com.example.spring_basic.global.api.resposne.ApiResponseCode;
import com.example.spring_basic.global.api.resposne.ErrorResponse;
import com.example.spring_basic.global.error.exception.notfound.NotFoundException;
import com.example.spring_basic.global.error.exception.badrequest.InvalidInputException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*
     * Bean Validation 예외 처리
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        InvalidInputException invalidInputException = new InvalidInputException(e.getBindingResult());
        return ErrorResponse.of(ApiResponseCode.BAD_REQUEST.getCode(),
                                ApiResponseCode.BAD_REQUEST.getMessage(),
                                request.getRequestURI(),
                                invalidInputException.getDescription());
    }

    /*
     * 기본 Not Found Exception
     */
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        return ErrorResponse.of(ApiResponseCode.NOT_FOUND.getCode(),
                                ApiResponseCode.NOT_FOUND.getMessage(),
                                request.getRequestURI(),
                                e.getMessage());
    }

    /*
     * 기타 모든 예외 발생
     */
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleUnhandledException(Exception e, HttpServletRequest request) {
        log.error("Exception", e);
        return ErrorResponse.of(ApiResponseCode.INTERNAL_SERVER_ERROR.getCode(),
                ApiResponseCode.INTERNAL_SERVER_ERROR.getMessage(),
                request.getRequestURI());
    }
}
