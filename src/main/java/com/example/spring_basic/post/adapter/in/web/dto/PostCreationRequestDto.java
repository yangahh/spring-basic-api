package com.example.spring_basic.post.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreationRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String author;
}
