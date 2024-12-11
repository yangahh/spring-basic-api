package com.example.spring_basic.global.error.exception.unauthorized;

import com.example.spring_basic.global.api.resposne.ApiResponseCode;
import com.example.spring_basic.global.error.exception.CustomException;

public class UnauthorizedException extends CustomException {

    public UnauthorizedException() {
        super(ApiResponseCode.UNAUTHORIZED);
    }

    public UnauthorizedException(String description) {
        super(ApiResponseCode.UNAUTHORIZED, description);
    }

    public UnauthorizedException(String requestUrl, String description) {
        super(ApiResponseCode.UNAUTHORIZED, description);
    }
}

