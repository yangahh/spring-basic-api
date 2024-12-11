package com.example.spring_basic.post.domain.port.in.usecase;

import com.example.spring_basic.post.domain.entity.Post;
import com.example.spring_basic.post.domain.port.in.dto.PostCreationInput;

public interface CreatePostUsecase {
    Post execute(PostCreationInput input);
}