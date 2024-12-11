package com.example.spring_basic.user.domain.port.out.repository;

import com.example.spring_basic.user.domain.entity.User;
import com.example.spring_basic.user.domain.port.in.dto.SignUpInputDto;

import java.util.Optional;

public interface UserRepository {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User save(SignUpInputDto input);
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
}
