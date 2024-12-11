package com.example.spring_basic.user.adapter.in.security.dto;

import com.example.spring_basic.user.adapter.in.security.vo.Role;
import com.example.spring_basic.user.adapter.out.persistence.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final UserModel user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  // role 반환하는 메서드
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                if (user.isStaff()) {
                    return Role.ROLE_ADMIN.name();
                }
                return Role.ROLE_USER.name();
            }
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public Long getUserId() {
        return user.getId();
    }
}
