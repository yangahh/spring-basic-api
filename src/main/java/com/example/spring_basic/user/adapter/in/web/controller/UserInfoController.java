package com.example.spring_basic.user.adapter.in.web.controller;

import com.example.spring_basic.user.adapter.in.security.dto.CustomUserDetails;
import com.example.spring_basic.user.adapter.in.web.dto.UserResponseDto;
import com.example.spring_basic.user.adapter.in.web.mapper.UserMapper;
import com.example.spring_basic.user.domain.entity.User;
import com.example.spring_basic.user.domain.port.in.usecase.GetUserUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/me")
@RequiredArgsConstructor
public class UserInfoController {
    private final GetUserUsecase getUserUsecase;
    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserInfo() {
        CustomUserDetails requestUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = getUserUsecase.execute(requestUser.getUserId());
        return new ResponseEntity<>(mapper.mapToResponseDto(user), HttpStatus.OK);
    }
}
