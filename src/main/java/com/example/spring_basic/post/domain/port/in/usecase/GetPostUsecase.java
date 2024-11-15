package com.example.spring_basic.post.domain.port.in.usecase;

import com.example.spring_basic.post.domain.entity.Post;

public interface GetPostUsecase {
    Post execute(Long id);
}
