package com.example.spring_basic.post.adapter.out.persistence.repository;

import com.example.spring_basic.global.error.exception.NotFoundException;
import com.example.spring_basic.post.adapter.out.persistence.mapper.JpaPostMapper;
import com.example.spring_basic.post.adapter.out.persistence.model.PostModel;
import com.example.spring_basic.post.domain.entity.Post;
import com.example.spring_basic.post.domain.port.in.dto.PostCreationInput;
import com.example.spring_basic.post.domain.port.out.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaPostMapper mapper;

    @Override
    public void save(PostCreationInput input) {
        jpaPostRepository.save(mapper.mapToJpaModelFromCreationInput(input));
    }

    @Override
    public List<Post> findAll() {
        return jpaPostRepository.findAll().stream()
                .map(mapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Post> findById(Long id) {
        return jpaPostRepository.findById(id)
                .map(mapper::mapToDomainEntity);
    }

    @Override
    public Post update(Post input) {
        return jpaPostRepository.findById(input.getId())
                .map(model -> updatePost(input, model))
                .orElseThrow(() -> new NotFoundException("Post not found with id: " + input.getId()));
    }

    private Post updatePost(Post input, PostModel model) {
        if (input.getTitle() != null) {
            model.setTitle(input.getTitle());
        }

        if (input.getContent() != null) {
            model.setContent(input.getContent());
        }

        return mapper.mapToDomainEntity(jpaPostRepository.save(model));
    }

    @Override
    public void delete(Long id) {
        jpaPostRepository.deleteById(id);
    }
}
