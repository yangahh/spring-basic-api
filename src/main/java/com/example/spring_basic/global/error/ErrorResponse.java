package com.example.spring_basic.global.error;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponse {
    private String code;
    private String requestUrl;
    private HttpStatus status;
    private String message;
}
