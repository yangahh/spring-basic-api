package com.example.spring_basic.global.error.exception.badrequest;

import org.springframework.validation.BindingResult;

public class InvalidInputException extends BadRequestException {
    public InvalidInputException(BindingResult bindingResult) {
        super(bindingResult);
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
