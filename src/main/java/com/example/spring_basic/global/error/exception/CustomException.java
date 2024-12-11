package com.example.spring_basic.global.error.exception;

import com.example.spring_basic.global.api.resposne.ApiResponseCode;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class CustomException extends RuntimeException {
    private final ApiResponseCode responseCode;
    private String description; // 추가 상세 정보

    public CustomException(ApiResponseCode responseCode) {
        super(responseCode.getMessage()); // RuntimeException의 메시지 필드 초기화
        this.responseCode = responseCode;
    }

    public CustomException(ApiResponseCode responseCode, String description) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
        this.description = description;
    }

    public CustomException(ApiResponseCode responseCode, BindingResult bindingResult) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
        this.description = buildDescriptionFromBindingResult(bindingResult);
    }

    private static String buildDescriptionFromBindingResult(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(error -> "[" + error.getField() + "] " + error.getDefaultMessage())
                .reduce("", (a, b) -> a + ", " + b);
    }
}
