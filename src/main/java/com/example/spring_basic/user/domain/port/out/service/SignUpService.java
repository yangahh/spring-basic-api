package com.example.spring_basic.user.domain.port.out.service;

import com.example.spring_basic.user.domain.entity.User;
import com.example.spring_basic.user.domain.port.in.dto.SignUpInputDto;
import com.example.spring_basic.user.domain.port.in.usecase.SignUpUsecase;
import com.example.spring_basic.user.domain.port.out.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService implements SignUpUsecase {
    private final UserRepository userRepository;

    @Override
    public User execute(SignUpInputDto signUpInputDto) {
        return userRepository.save(signUpInputDto);
    }
}
