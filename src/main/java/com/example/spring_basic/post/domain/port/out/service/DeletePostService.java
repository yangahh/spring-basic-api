package com.example.spring_basic.post.domain.port.out.service;

import com.example.spring_basic.post.domain.port.in.usecase.DeletePostUsecase;
import com.example.spring_basic.post.domain.port.out.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePostService implements DeletePostUsecase {
    private final PostRepository postRepository;

    @Override
    @Transactional
    public void execute(Long id, Long requestUserId) {
        postRepository.delete(id, requestUserId);
    }

    // TODO: ownership check -> Usecase단으로 옮기기
}
