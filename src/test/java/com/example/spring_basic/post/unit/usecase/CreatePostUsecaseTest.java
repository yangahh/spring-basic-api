package com.example.spring_basic.post.unit.usecase;

import com.example.spring_basic.post.domain.port.in.usecase.CreatePostUsecase;
import com.example.spring_basic.post.domain.port.out.service.CreatePostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(CreatePostService.class)
public class CreatePostUsecaseTest {

    @Autowired
    private CreatePostUsecase createPostUsecase;

    @DisplayName("input dto를 받아서 게시글을 작성한다.")
    @Test
    void createPostTest() {
        // given


        // when


        // then

    }
}
