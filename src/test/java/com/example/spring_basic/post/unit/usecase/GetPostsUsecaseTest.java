package com.example.spring_basic.post.unit.usecase;

import com.example.spring_basic.post.domain.port.out.service.GetPostsService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(GetPostsService.class)
public class GetPostsUsecaseTest {
}
