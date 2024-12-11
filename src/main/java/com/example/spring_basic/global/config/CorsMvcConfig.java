package com.example.spring_basic.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 시큐리티단에서 바로 응답이 나가는 경우 SecurityConfig에 설정한 CORS 설정만 추가하면 잘 동작하지만,
 Controller단에서 응답이 나가는 경우 가끔 SecurityConfig CORS 설정을 추가하셔도 CORS 문제가 발생하는 경우가 있기 때문에
 MvcConfigurer에서 CORS 설정을 추가해야 한다.
 */
@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:3000");
    }
}
