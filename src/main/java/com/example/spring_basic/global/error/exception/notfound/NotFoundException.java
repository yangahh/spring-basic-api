package com.example.spring_basic.global.error.exception.notfound;

import com.example.spring_basic.global.api.resposne.ApiResponseCode;
import com.example.spring_basic.global.error.exception.CustomException;
import org.springframework.validation.BindingResult;

public class NotFoundException extends CustomException {
    public NotFoundException() {
        super(ApiResponseCode.NOT_FOUND);
    }

    public NotFoundException(BindingResult bindingResult) {
        super(ApiResponseCode.NOT_FOUND, bindingResult);
    }

    public NotFoundException(String description) {
        super(ApiResponseCode.NOT_FOUND, description);
    }
}
