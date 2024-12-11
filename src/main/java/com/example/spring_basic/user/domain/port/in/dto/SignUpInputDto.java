package com.example.spring_basic.user.domain.port.in.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SignUpInputDto(
    @NotNull String username,
    @NotNull String password,
    @NotNull String email,
    String nickname
) { }
