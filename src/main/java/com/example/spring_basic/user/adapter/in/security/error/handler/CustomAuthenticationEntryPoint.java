package com.example.spring_basic.user.adapter.in.security.error.handler;

import com.example.spring_basic.global.api.resposne.ApiResponseCode;
import com.example.spring_basic.global.api.resposne.ErrorResponse;
import com.example.spring_basic.global.error.exception.unauthorized.JwtAuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.MimeTypeUtils;

import java.io.IOException;

import static com.example.spring_basic.global.constant.AttributeConstant.ORIGINAL_REQUEST_URL;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String originalRequestUrl = (String) request.getAttribute(ORIGINAL_REQUEST_URL);
        String description = "로그인이 필요합니다.";
        Throwable cause = authException.getCause();
        if (cause instanceof JwtAuthenticationException) { // JwtException 발생 시
            // description = getJwtExceptionDetailMessage((JwtException) authException.getCause());
            description = cause.getMessage();
        }

        ErrorResponse errorResponse = ErrorResponse.of(ApiResponseCode.UNAUTHORIZED.getCode(),
                ApiResponseCode.UNAUTHORIZED.getMessage(),
                originalRequestUrl != null ? originalRequestUrl : request.getRequestURI(),
                description);

        response.setCharacterEncoding("UTF-8");
        response.setStatus(ApiResponseCode.UNAUTHORIZED.getCode());
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

    // private String getJwtExceptionDetailMessage(Exception exception) {
    //     String errorMessage = "JWT token error";
    //
    //     if (exception instanceof ExpiredJwtException) {
    //         errorMessage = "토큰이 만료되었습니다.";
    //     } else if (exception instanceof MalformedJwtException) {
    //         errorMessage = "잘못된 형식의 토큰입니다.";
    //     } else if (exception instanceof UnsupportedJwtException) {
    //         errorMessage = "지원되지 않는 토큰입니다.";
    //     } else if (exception instanceof SignatureException) {
    //         errorMessage = "잘못된 서명입니다.";
    //
    //     }
    //
    //     return errorMessage;
    // }
}
