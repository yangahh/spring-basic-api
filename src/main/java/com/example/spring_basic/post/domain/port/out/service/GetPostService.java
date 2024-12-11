package com.example.spring_basic.post.domain.port.out.service;

import com.example.spring_basic.global.error.exception.notfound.NotFoundException;
import com.example.spring_basic.post.domain.entity.Post;
import com.example.spring_basic.post.domain.port.in.usecase.GetPostUsecase;
import com.example.spring_basic.post.domain.port.out.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPostService implements GetPostUsecase {

    private final PostRepository postRepository;

    @Override
    public Post execute(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found with id: " + id));
    }
}
