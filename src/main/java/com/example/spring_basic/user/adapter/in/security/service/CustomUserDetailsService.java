package com.example.spring_basic.user.adapter.in.security.service;

import com.example.spring_basic.user.adapter.in.security.dto.CustomUserDetails;
import com.example.spring_basic.user.adapter.out.persistence.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // DB에서 조회 후 UserDetails를 반환
        return jpaUserRepository.findByUsername(username)
                .map(CustomUserDetails::new)
                .orElse(null);
    }
}
