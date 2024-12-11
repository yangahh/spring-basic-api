package com.example.spring_basic.user.adapter.out.persistence.repository;

import com.example.spring_basic.user.adapter.out.persistence.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
