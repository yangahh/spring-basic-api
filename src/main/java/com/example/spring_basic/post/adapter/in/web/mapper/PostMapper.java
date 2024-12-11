package com.example.spring_basic.post.adapter.in.web.mapper;

import com.example.spring_basic.post.adapter.in.web.dto.PostCreationRequestDto;
import com.example.spring_basic.post.adapter.in.web.dto.PostResponseDto;
import com.example.spring_basic.post.adapter.in.web.dto.PostUpdateRequestDto;
import com.example.spring_basic.post.domain.entity.Post;
import com.example.spring_basic.post.domain.port.in.dto.PostCreationInput;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public PostCreationInput mapToDomainInputDtoFromRequestDto(PostCreationRequestDto requestDto, Long userId) {
        return PostCreationInput.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .userId(userId)
                .build();
    }

    public Post mapToDomainEntityFromUpdateRequest(Long id, PostUpdateRequestDto requestDto) {
        return Post.builder()
                .id(id)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();
    }

    public PostResponseDto mapToResponseDto(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .userId(post.getUser().getId())
                .username(post.getUser().getUsername())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
