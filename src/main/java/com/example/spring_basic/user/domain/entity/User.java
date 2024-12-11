package com.example.spring_basic.user.domain.entity;

import com.example.spring_basic.user.domain.vo.AccountStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @NotNull
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String nickname;

    @NotNull
    private AccountStatus accountStatus;

    private LocalDateTime lastLoginAt;

    @NotNull
    private boolean isStaff;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;
}
