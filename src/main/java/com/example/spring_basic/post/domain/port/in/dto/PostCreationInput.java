package com.example.spring_basic.post.domain.port.in.dto;

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

    // TODO: Change to User ID
    @NotNull
    private String author;
}
