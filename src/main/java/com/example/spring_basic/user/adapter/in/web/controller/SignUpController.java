package com.example.spring_basic.user.adapter.in.web.controller;

import com.example.spring_basic.user.adapter.in.web.dto.SignUpRequestDto;
import com.example.spring_basic.user.adapter.in.web.dto.UserResponseDto;
import com.example.spring_basic.user.adapter.in.web.mapper.UserMapper;
import com.example.spring_basic.user.domain.entity.User;
import com.example.spring_basic.user.domain.port.in.usecase.SignUpUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpUsecase signUpUsecase;
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        User user = signUpUsecase.execute(mapper.mapToDomainInputDtoFromRequestDto(signUpRequestDto));
        return new ResponseEntity<>(mapper.mapToResponseDto(user), HttpStatus.CREATED);
    }
}
