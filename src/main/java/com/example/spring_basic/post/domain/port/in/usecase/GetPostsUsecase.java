package com.example.spring_basic.post.domain.port.in.usecase;

import com.example.spring_basic.post.domain.entity.Post;

import java.util.List;

public interface GetPostsUsecase {
    List<Post> execute();
}
