package com.example.spring_basic.post.domain.port.out.service;

import com.example.spring_basic.post.domain.entity.Post;
import com.example.spring_basic.post.domain.port.in.usecase.GetPostsUsecase;
import com.example.spring_basic.post.domain.port.out.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPostsService implements GetPostsUsecase {

    private final PostRepository postRepository;

    @Override
    public List<Post> execute() {
        // TODO: add filter
        return postRepository.findAll();
    }
}
