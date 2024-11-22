package com.example.spring_basic.post.adapter.out.persistence.mapper;

import com.example.spring_basic.post.adapter.out.persistence.model.PostModel;
import com.example.spring_basic.post.domain.entity.Post;
import com.example.spring_basic.post.domain.port.in.dto.PostCreationInput;
import org.springframework.stereotype.Component;

@Component
public class JpaPostMapper {
    public PostModel mapToJpaModelFromCreationInput(PostCreationInput input) {
        return PostModel.builder()
                .title(input.getTitle())
                .content(input.getContent())
                .author(input.getAuthor())
                .build();
    }

    public Post mapToDomainEntity(PostModel model) {
        return Post.builder()
                .id(model.getId())
                .title(model.getTitle())
                .content(model.getContent())
                .author(model.getAuthor())
                .createdAt(model.getCreatedAt())
                .updatedAt(model.getUpdatedAt())
                .build();
    }

    public PostModel mapToJpaModel(Post post) {
        return PostModel.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
