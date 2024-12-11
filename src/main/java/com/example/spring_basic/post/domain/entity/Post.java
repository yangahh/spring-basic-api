package com.example.spring_basic.post.domain.entity;

import com.example.spring_basic.user.domain.entity.User;
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
public class Post {
    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private User user;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;

}
