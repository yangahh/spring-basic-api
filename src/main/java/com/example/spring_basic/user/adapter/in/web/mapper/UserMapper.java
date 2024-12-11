package com.example.spring_basic.user.adapter.in.web.mapper;

import com.example.spring_basic.user.adapter.in.web.dto.SignUpRequestDto;
import com.example.spring_basic.user.adapter.in.web.dto.UserResponseDto;
import com.example.spring_basic.user.domain.entity.User;
import com.example.spring_basic.user.domain.port.in.dto.SignUpInputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SignUpInputDto mapToDomainInputDtoFromRequestDto(SignUpRequestDto requestDto) {
        return SignUpInputDto.builder()
                .username(requestDto.username())
                .email(requestDto.email())
                .password(encryptPassword(requestDto.password()))
                .nickname(requestDto.nickname())
                .build();
    }

    private String encryptPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public UserResponseDto mapToResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .accountStatus(user.getAccountStatus())
                .isStaff(user.isStaff())
                .lastLoginAt(user.getLastLoginAt())
                .build();
    }
}
