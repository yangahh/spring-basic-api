package com.example.spring_basic.user.domain.port.in.usecase;

import com.example.spring_basic.user.domain.entity.User;

public interface GetUserUsecase {
    User execute(Long userId);
}
