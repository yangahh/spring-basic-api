package com.example.spring_basic.global.error;

import com.example.spring_basic.global.error.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*
     * Not Found Exception
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        log.error("NotFoundException", e);
        return buildErrorResponse(e, request, HttpStatus.NOT_FOUND);
    }

    /*
     * 기타 모든 예외 발생
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("Exception", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception e, HttpServletRequest request, HttpStatus httpStatus) {
        ErrorResponse res = ErrorResponse.builder()
                .code(httpStatus.toString())
                .requestUrl(request.getRequestURI())
                .status(httpStatus)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(res, httpStatus);
    }


}
