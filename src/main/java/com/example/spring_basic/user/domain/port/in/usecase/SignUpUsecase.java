package com.example.spring_basic.user.domain.port.in.usecase;

import com.example.spring_basic.user.domain.entity.User;
import com.example.spring_basic.user.domain.port.in.dto.SignUpInputDto;

public interface SignUpUsecase {
    User execute(SignUpInputDto signUpInputDto);
}
