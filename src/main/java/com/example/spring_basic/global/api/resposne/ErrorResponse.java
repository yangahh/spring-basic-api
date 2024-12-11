package com.example.spring_basic.global.api.resposne;

import lombok.*;

@Getter
public class ErrorResponse {
    private final int statusCode;
    private final String message;
    private final String requestUrl;
    private String description;

    @Builder  // 외부에서는 builder를 통해 객체를 생성할 수 없도록 설정
    private ErrorResponse(int statusCode, String message, String requestUrl, String description) {
        this.statusCode = statusCode;
        this.message = message;
        this.requestUrl = requestUrl;
        this.description = description;
    }

    public static ErrorResponse of(int statusCode, String message, String requestUrl) {
        return ErrorResponse.builder()
                .statusCode(statusCode)
                .message(message)
                .requestUrl(requestUrl)
                .build();
    }

    public static ErrorResponse of(int statusCode, String message, String requestUrl, String description) {
        return ErrorResponse.builder()
                .statusCode(statusCode)
                .message(message)
                .requestUrl(requestUrl)
                .description(description)
                .build();
    }
}
