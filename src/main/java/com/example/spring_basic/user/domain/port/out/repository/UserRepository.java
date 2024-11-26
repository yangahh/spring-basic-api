package com.example.spring_basic.user.domain.port.out.repository;

import com.example.spring_basic.user.domain.entity.User;
import com.example.spring_basic.user.domain.port.in.dto.SignUpInputDto;

import java.util.Optional;

public interface UserRepository {
    User save(SignUpInputDto input);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
