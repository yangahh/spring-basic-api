package com.example.spring_basic.user.adapter.in.security.vo;

import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public enum Role {
    ROLE_USER( null),
    ROLE_ADMIN(null);

    private Set<Privilege> privileges;
}
