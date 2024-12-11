package com.example.spring_basic.global.error.exception.badrequest;

import com.example.spring_basic.global.api.resposne.ApiResponseCode;
import com.example.spring_basic.global.error.exception.CustomException;
import org.springframework.validation.BindingResult;

public class BadRequestException extends CustomException {
    public BadRequestException() {
        super(ApiResponseCode.BAD_REQUEST);
    }

    public BadRequestException(BindingResult bindingResult) {
        super(ApiResponseCode.BAD_REQUEST, bindingResult);
    }

    public BadRequestException(String description) {
        super(ApiResponseCode.BAD_REQUEST, description);
    }
}
