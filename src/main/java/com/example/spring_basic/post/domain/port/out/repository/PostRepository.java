package com.example.spring_basic.post.domain.port.out.repository;

import com.example.spring_basic.post.domain.entity.Post;
import com.example.spring_basic.post.domain.port.in.dto.PostCreationInput;
import com.example.spring_basic.user.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(PostCreationInput input, User user);
    List<Post> findAll();  // TODO: add filter
    Optional<Post> findById(Long id);
    Post update(Post input, Long requestUserId);
    void delete(Long id, Long requestUserId);
}
