package com.example.spring_basic.post.adapter.out.persistence.mapper;

import com.example.spring_basic.post.adapter.out.persistence.model.PostModel;
import com.example.spring_basic.post.domain.entity.Post;
import com.example.spring_basic.post.domain.port.in.dto.PostCreationInput;
import com.example.spring_basic.user.adapter.out.persistence.mapper.JpaUserMapper;
import com.example.spring_basic.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaPostMapper {
    private final JpaUserMapper jpaUserMapper;

    public PostModel mapToJpaModelFromCreationInput(PostCreationInput input, User user) {
        return PostModel.builder()
                .title(input.getTitle())
                .content(input.getContent())
                .user(jpaUserMapper.mapToJpaModel(user))
                .build();
    }

    public Post mapToDomainEntity(PostModel model) {
        return Post.builder()
                .id(model.getId())
                .title(model.getTitle())
                .content(model.getContent())
                .user(jpaUserMapper.mapToDomainEntity(model.getUser()))
                .createdAt(model.getCreatedAt())
                .updatedAt(model.getUpdatedAt())
                .build();
    }

    public PostModel mapToJpaModel(Post post) {
        return PostModel.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .user(jpaUserMapper.mapToJpaModel(post.getUser()))
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
