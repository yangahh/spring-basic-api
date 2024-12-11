package com.example.spring_basic.user.adapter.in.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static com.example.spring_basic.global.utils.regexp.ValidationPatterns.LOGIN_ID_REGEXP;
import static com.example.spring_basic.global.utils.regexp.ValidationPatterns.PASSWORD_REGEXP;

public record SignUpRequestDto(
        @NotNull @Pattern(regexp = LOGIN_ID_REGEXP) String username,
        @NotNull @Pattern(regexp = PASSWORD_REGEXP) String password,
        @NotNull @Email String email,
        String nickname
) { }
