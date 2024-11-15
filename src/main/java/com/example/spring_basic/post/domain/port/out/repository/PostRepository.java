package com.example.spring_basic.post.domain.port.out.repository;

import com.example.spring_basic.post.domain.entity.Post;
import com.example.spring_basic.post.domain.port.in.dto.PostCreationInput;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    void save(PostCreationInput input);
    List<Post> findAll();  // TODO: add filter
    Optional<Post> findById(Long id);
    Post update(Post input);
    void delete(Long id);
}
