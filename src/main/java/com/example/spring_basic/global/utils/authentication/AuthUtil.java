package com.example.spring_basic.global.utils.authentication;

import com.example.spring_basic.global.error.exception.unauthorized.UnauthorizedException;
import com.example.spring_basic.user.adapter.in.security.dto.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {
    public static Long getUserId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            return principal.getUserId();
        } catch (Exception e) {
            throw new UnauthorizedException();
        }
    }

    public static String getUsername() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            return principal.getUsername();
        } catch (Exception e) {
            throw new UnauthorizedException();
        }
    }
}
