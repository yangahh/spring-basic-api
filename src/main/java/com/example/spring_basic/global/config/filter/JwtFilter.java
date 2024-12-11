package com.example.spring_basic.global.config.filter;

import com.example.spring_basic.global.error.exception.unauthorized.JwtAuthenticationException;
import com.example.spring_basic.global.utils.jwt.JwtUtil;
import com.example.spring_basic.user.adapter.in.security.dto.CustomUserDetails;
import com.example.spring_basic.user.adapter.out.persistence.repository.JpaUserRepository;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import static com.example.spring_basic.global.constant.AttributeConstant.ORIGINAL_REQUEST_URL;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final JpaUserRepository jpaUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        if (requestUri.matches("^\\/login(?:\\/.*)?$")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (requestUri.matches("^\\/oauth2(?:\\/.*)?$")) {
            filterChain.doFilter(request, response);
            return;
        }

        request.setAttribute(ORIGINAL_REQUEST_URL, request.getRequestURI());

        try {
            Optional<String> token = jwtUtil.extractToken(request);
            if (token.isEmpty() || jwtUtil.isExpired(token.get())) {
                filterChain.doFilter(request, response);
                return;
            }

            setAuthenticationAtContextHolder(token.get());
            filterChain.doFilter(request, response);

        } catch (JwtException e) {
            SecurityContextHolder.clearContext();
            throw new JwtAuthenticationException(e.getMessage(), e);
        }
    }

    // Session에 Authentication 객체를 저장
    private void setAuthenticationAtContextHolder(String token) {
        CustomUserDetails customUserDetails = jpaUserRepository.findByUsername(jwtUtil.getUsername(token))
                .map(CustomUserDetails::new)
                .orElse(null);

        if (customUserDetails != null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
