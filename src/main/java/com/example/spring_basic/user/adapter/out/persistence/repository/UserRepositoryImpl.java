package com.example.spring_basic.user.adapter.out.persistence.repository;

import com.example.spring_basic.user.adapter.out.persistence.mapper.JpaUserMapper;
import com.example.spring_basic.user.adapter.out.persistence.model.UserModel;
import com.example.spring_basic.user.domain.entity.User;
import com.example.spring_basic.user.domain.port.in.dto.SignUpInputDto;
import com.example.spring_basic.user.domain.port.out.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;
    private final JpaUserMapper mapper;

    @Override
    public User save(SignUpInputDto input) {
        UserModel save = jpaUserRepository.save(mapper.mapToJpaModelFromSignUpInput(input));
        return mapper.mapToDomainEntity(save);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username)
                .map(mapper::mapToDomainEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id)
                .map(mapper::mapToDomainEntity);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaUserRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }
}
