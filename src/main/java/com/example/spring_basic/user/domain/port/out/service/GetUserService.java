package com.example.spring_basic.user.domain.port.out.service;

import com.example.spring_basic.global.error.exception.notfound.NotFoundException;
import com.example.spring_basic.user.domain.entity.User;
import com.example.spring_basic.user.domain.port.in.usecase.GetUserUsecase;
import com.example.spring_basic.user.domain.port.out.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserService implements GetUserUsecase {
    private final UserRepository userRepository;

    @Override
    public User execute(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
