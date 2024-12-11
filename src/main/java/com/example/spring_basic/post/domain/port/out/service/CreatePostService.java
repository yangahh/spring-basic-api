package com.example.spring_basic.post.domain.port.out.service;

import com.example.spring_basic.global.error.exception.notfound.NotFoundException;
import com.example.spring_basic.post.domain.entity.Post;
import com.example.spring_basic.post.domain.port.in.dto.PostCreationInput;
import com.example.spring_basic.post.domain.port.in.usecase.CreatePostUsecase;
import com.example.spring_basic.post.domain.port.out.repository.PostRepository;
import com.example.spring_basic.user.domain.entity.User;
import com.example.spring_basic.user.domain.port.out.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePostService implements CreatePostUsecase {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Post execute(PostCreationInput input) {
        return postRepository.save(input, getUser(input.getUserId()));
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
    }
}
