package com.example.spring_basic.global.error.exception.forbidden;

import com.example.spring_basic.global.api.resposne.ApiResponseCode;
import com.example.spring_basic.global.error.exception.CustomException;

public class ForbiddenException extends CustomException {
    public ForbiddenException() {
        super(ApiResponseCode.FORBIDDEN);
    }

    public ForbiddenException(String description) {
        super(ApiResponseCode.FORBIDDEN, description);
    }
}
