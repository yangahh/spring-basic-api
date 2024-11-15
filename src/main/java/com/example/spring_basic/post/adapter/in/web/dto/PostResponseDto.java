package com.example.spring_basic.post.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author; // TODO: Change to User ID, username
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
