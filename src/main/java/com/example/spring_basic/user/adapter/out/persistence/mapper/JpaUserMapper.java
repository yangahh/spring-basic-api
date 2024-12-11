package com.example.spring_basic.user.adapter.out.persistence.mapper;

import com.example.spring_basic.user.adapter.out.persistence.model.UserModel;
import com.example.spring_basic.user.domain.entity.User;
import com.example.spring_basic.user.domain.port.in.dto.SignUpInputDto;
import com.example.spring_basic.user.domain.vo.AccountStatus;
import org.springframework.stereotype.Component;

@Component
public class JpaUserMapper {
    public UserModel mapToJpaModelFromSignUpInput(SignUpInputDto input) {
        return UserModel.builder()
                .username(input.username())
                .password(input.password())
                .email(input.email())
                .nickname(input.nickname())
                .status(AccountStatus.ACTIVE)
                .build();
    }
    
    public User mapToDomainEntity(UserModel model) {
        return User.builder()
                .id(model.getId())
                .username(model.getUsername())
                .password(model.getPassword())
                .email(model.getEmail())
                .nickname(model.getNickname())
                .lastLoginAt(model.getLastLoginAt())
                .accountStatus(model.getStatus())
                .isStaff(model.isStaff())
                .createdAt(model.getCreatedAt())
                .updatedAt(model.getUpdatedAt())
                .build();
    }

    public UserModel mapToJpaModel(User user) {
        return UserModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .status(user.getAccountStatus())
                .isStaff(user.isStaff())
                .lastLoginAt(user.getLastLoginAt())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
