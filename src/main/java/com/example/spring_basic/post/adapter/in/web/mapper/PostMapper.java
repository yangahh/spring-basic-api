package com.example.spring_basic.post.adapter.in.web.mapper;

import com.example.spring_basic.post.adapter.in.web.dto.PostCreationRequestDto;
import com.example.spring_basic.post.adapter.in.web.dto.PostResponseDto;
import com.example.spring_basic.post.adapter.in.web.dto.PostUpdateRequestDto;
import com.example.spring_basic.post.domain.entity.Post;
import com.example.spring_basic.post.domain.port.in.dto.PostCreationInput;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public PostCreationInput mapToDomainInputDtoFromRequestDto(PostCreationRequestDto requestDto) {
        return PostCreationInput.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .author(requestDto.getAuthor())
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
                .author(post.getAuthor())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
