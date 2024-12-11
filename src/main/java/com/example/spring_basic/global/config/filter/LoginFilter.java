package com.example.spring_basic.global.config.filter;

import com.example.spring_basic.global.api.resposne.ApiResponse;
import com.example.spring_basic.global.api.resposne.ApiResponseCode;
import com.example.spring_basic.global.utils.jwt.JwtUtil;
import com.example.spring_basic.user.adapter.in.security.dto.CustomUserDetails;
import com.example.spring_basic.user.adapter.in.security.dto.LoginRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.MimeTypeUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

import static com.example.spring_basic.global.constant.AttributeConstant.ORIGINAL_REQUEST_URL;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (request.getContentType().equals(MimeTypeUtils.APPLICATION_JSON_VALUE)) {
            return handleJsonRequest(request);
        }
        return handleFormRequest(request);
    }

    private Authentication handleJsonRequest(HttpServletRequest request) {
        try {
            LoginRequestDto loginRequestDto = objectMapper.readValue(
                    request.getReader().lines().collect(Collectors.joining()), LoginRequestDto.class);
            String username = loginRequestDto.getUsername();
            String password = loginRequestDto.getPassword();

            // 스프링 시큐리티에서 username과 password를 검증하기 위해서는 UsernamePasswordAuthenticationToken이라는 token에 담아야 함
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password, null);
            setDetails(request, authRequest);

            // authToken을 AuthenticationManager로 던져서 인증을 시도하도록 함
            return authenticationManager.authenticate(authRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Authentication handleFormRequest(HttpServletRequest request) {
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password, null);
        setDetails(request, authRequest);

        return authenticationManager.authenticate(authRequest);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomUserDetails userDetails = (CustomUserDetails) authResult.getPrincipal(); // 인증 성공한 사용자 정보를 가져옴
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        String role = authorities.stream().findFirst().toString();

        String token = jwtUtil.generateAccessToken(userDetails.getUserId(), userDetails.getUsername(), role);
        jwtUtil.sendToken(response, token);

        response.setCharacterEncoding("utf-8");
        response.setStatus(ApiResponseCode.OK.getCode());
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);

        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .statusCode(ApiResponseCode.OK.getCode())
                .message("Success Login")
                .build();
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }

    // @Override
    // protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    //     response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    // }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // 실패 시 CustomAuthenticationFailureHandler 호출
        request.setAttribute(ORIGINAL_REQUEST_URL, request.getRequestURI());
        authenticationFailureHandler.onAuthenticationFailure(request, response, failed);
    }
}
