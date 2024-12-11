package com.example.spring_basic.post.domain.port.in.dto;

import com.example.spring_basic.user.domain.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreationInput {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Long userId;
}
