package com.example.spring_basic.user.adapter.in.security.error.handler;

import com.example.spring_basic.global.api.resposne.ApiResponseCode;
import com.example.spring_basic.global.api.resposne.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.spring_basic.global.constant.AttributeConstant.ORIGINAL_REQUEST_URL;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, JsonProcessingException {
        ErrorResponse errorResponse = ErrorResponse.of(ApiResponseCode.UNAUTHORIZED.getCode(),
                ApiResponseCode.UNAUTHORIZED.getMessage(),
                request.getAttribute(ORIGINAL_REQUEST_URL).toString(),
                "아이디나 비밀번호가 일치하지 않습니다.");

        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
