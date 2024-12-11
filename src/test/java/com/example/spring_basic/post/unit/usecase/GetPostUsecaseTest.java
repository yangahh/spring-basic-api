package com.example.spring_basic.post.unit.usecase;

import com.example.spring_basic.post.domain.port.out.service.GetPostService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(GetPostService.class)
public class GetPostUsecaseTest {
}
