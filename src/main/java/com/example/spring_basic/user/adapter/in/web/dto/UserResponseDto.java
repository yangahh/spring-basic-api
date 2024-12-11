package com.example.spring_basic.user.adapter.in.web.dto;

import com.example.spring_basic.user.domain.vo.AccountStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponseDto(
        Long id,
        String username,
        String email,
        String nickname,
        AccountStatus accountStatus,
        boolean isStaff,
        LocalDateTime lastLoginAt
) { }
