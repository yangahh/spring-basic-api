package com.example.spring_basic.global.utils.jwt;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtil {
    @Value("${spring.jwt.secret}")
    private String secretKey;

    @Value("${spring.jwt.prefix}")
    private String tokenPrefix;

    @Value("${spring.jwt.access.expired-ms}")
    private Long accessTokenExpiredMs;

    @Value("${spring.jwt.access.header}")
    private String accessTokenHeader;

    @Value("${spring.jwt.refresh.expired-ms}")
    private Long refreshTokenExpiredMs;

    @Value("${spring.jwt.refresh.header}")
    private String refreshTokenHeader;

    private SecretKey jwtSecretKey;

    @PostConstruct
    private void setJwtSecretKey() {
        this.jwtSecretKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),
                                            Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String generateAccessToken(Long userId, String username, String role) {
        return Jwts.builder()
                .subject(userId.toString()) // 토큰 제목이라는 뜻으로 주로 식별자를 넣음
                .claim("username", username)  // claim은 JWT payload에 저장되는 정보로 토큰에 추가로 저장하고 싶은 정보를 key-value 형태로 넣음
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiredMs))
                .signWith(jwtSecretKey)
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser().verifyWith(jwtSecretKey).build() // 이 key로 토큰을 검증하고 builder를 반환
                .parseSignedClaims(token)
                .getPayload()
                .get("username", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(jwtSecretKey).build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

    public boolean isExpired(String token) {
        return Jwts.parser().verifyWith(jwtSecretKey).build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());  // 토큰의 만료 시간이 현재 시간보다 이전이면 true를 반환 >> 토큰이 만료되었음을 의미
    }

    public void sendToken(HttpServletResponse response, String token) {
        response.addHeader(accessTokenHeader, tokenPrefix + token);
    }

    public Optional<String> extractToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessTokenHeader))
                .filter(token -> token.startsWith(tokenPrefix))
                .map(token -> token.replace(tokenPrefix, ""));
    }

    // TODO: refresh token은 쿠키로 전달
}
