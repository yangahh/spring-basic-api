package com.example.spring_basic.user.adapter.in.security.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}
