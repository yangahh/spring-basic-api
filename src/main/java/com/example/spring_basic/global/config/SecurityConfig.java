package com.example.spring_basic.global.config;

import com.example.spring_basic.global.config.filter.JwtFilter;
import com.example.spring_basic.global.config.filter.LoginFilter;
import com.example.spring_basic.global.utils.jwt.JwtUtil;
import com.example.spring_basic.user.adapter.in.security.error.handler.CustomAccessDeniedHandler;
import com.example.spring_basic.user.adapter.in.security.error.handler.CustomAuthenticationEntryPoint;
import com.example.spring_basic.user.adapter.in.security.error.handler.CustomAuthenticationFailureHandler;
import com.example.spring_basic.user.adapter.out.persistence.repository.JpaUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;
    private final JpaUserRepository jpaUserRepository;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // cors 설정
        http.cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                configuration.setAllowedMethods(Collections.singletonList("*"));
                configuration.setAllowCredentials(true);  // 클라이언트가 인증 정보를 포함한 요청(예: 쿠키, 인증 헤더)을 보낼 수 있도록 허용. 클라이언트는 withCredentials: true 설정을 사용하여 인증 정보(Credentials)를 서버에 보낸다. 즉, 클라이언트에서 true로 설정하면 서버에서도 true로 설정해야 한다.
                configuration.setAllowedHeaders(Collections.singletonList("*"));
                configuration.setMaxAge(3600L); // preflight 요청 결과를 캐싱할 시간
                configuration.setExposedHeaders(Collections.singletonList("Authorization"));  // 클라이언트에게 response할때 노출할 헤더 추가
                return configuration;
            }
        }));

        // csrf disable: jwt를 사용하기 때문에
        http.csrf(auth -> auth.disable());

        // Form 로그인 방식 disable
        http.formLogin(auth -> auth.disable());

        // Basic 로그인 방식 disable
        http.httpBasic(auth -> auth.disable());

        // 경로별 인가 작업
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/signup").permitAll()
                .requestMatchers(HttpMethod.GET, "/posts").permitAll()
                .requestMatchers(HttpMethod.GET, "/posts/*").permitAll()
                .anyRequest().authenticated());

        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())  // 401 Unauthorized 처리
                .accessDeniedHandler(new CustomAccessDeniedHandler()));  // 403 Forbidden 처리

        // JWT 필터 추가
        http.addFilterBefore(new JwtFilter(jwtUtil, jpaUserRepository), LoginFilter.class);

        // 로그인 필터 추가
        http.addFilterAt(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        // 세션 stateless 설정
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    protected LoginFilter getAuthenticationFilter() throws Exception {
        LoginFilter authFilter = new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, authenticationFailureHandler);
        authFilter.setFilterProcessesUrl("/login");
        authFilter.setUsernameParameter("username"); // username 설정
        authFilter.setPasswordParameter("password"); // password 설정
        // authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);		// SuccessHandler 설정
        // authFilter.setAuthenticationFailureHandler(authenticationFailureHandler);		// FailuerHandler 설정
        return authFilter;
    }
}
