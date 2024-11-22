package com.example.spring_basic.post.domain.port.out.service;

import com.example.spring_basic.post.domain.port.in.dto.PostCreationInput;
import com.example.spring_basic.post.domain.port.in.usecase.CreatePostUsecase;
import com.example.spring_basic.post.domain.port.out.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostService implements CreatePostUsecase {

    private final PostRepository postRepository;

    @Override
    public void execute(PostCreationInput input) {
        postRepository.save(input);
    }
}
