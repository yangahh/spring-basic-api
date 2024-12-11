package com.example.spring_basic.global.api.resposne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class ApiResponse<T> {
    private int statusCode;
    private String message;
    private T data;

    public ApiResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(ApiResponseCode.OK.getCode(), ApiResponseCode.OK.getMessage(), data);
    }

    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>(ApiResponseCode.OK.getCode(), message, data);
    }

    public static <T> ApiResponse<T> created(String message) {
        return new ApiResponse<>(ApiResponseCode.CREATED.getCode(), message);
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(ApiResponseCode.CREATED.getCode(), ApiResponseCode.CREATED.getMessage(), data);
    }

    public static <T> ApiResponse<T> created(String message, T data) {
        return new ApiResponse<>(ApiResponseCode.CREATED.getCode(), message, data);
    }

    public static <T> ApiResponse<T> noContent() {
        return new ApiResponse<>(ApiResponseCode.NO_CONTENT.getCode(), ApiResponseCode.NO_CONTENT.getMessage());
    }
}
