package com.example.spring_basic.post.domain.port.out.service;

import com.example.spring_basic.post.domain.entity.Post;
import com.example.spring_basic.post.domain.port.in.usecase.UpdatePostUsecase;
import com.example.spring_basic.post.domain.port.out.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePostService implements UpdatePostUsecase {
    private final PostRepository postRepository;

    @Override
    @Transactional
    public Post execute(Post post) {
        return postRepository.update(post);
    }
}
