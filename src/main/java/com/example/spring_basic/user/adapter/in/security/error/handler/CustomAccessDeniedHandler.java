package com.example.spring_basic.user.adapter.in.security.error.handler;

import com.example.spring_basic.global.api.resposne.ApiResponseCode;
import com.example.spring_basic.global.api.resposne.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.util.MimeTypeUtils;

import java.io.IOException;

import static com.example.spring_basic.global.constant.AttributeConstant.ORIGINAL_REQUEST_URL;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String originalRequestUrl = (String) request.getAttribute(ORIGINAL_REQUEST_URL);
        ErrorResponse errorResponse = ErrorResponse.of(ApiResponseCode.FORBIDDEN.getCode(),
                ApiResponseCode.FORBIDDEN.getMessage(),
                originalRequestUrl != null ? originalRequestUrl : request.getRequestURI(),
                "접근 권한이 없습니다.");

        response.setCharacterEncoding("utf-8");
        response.setStatus(ApiResponseCode.FORBIDDEN.getCode());
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
