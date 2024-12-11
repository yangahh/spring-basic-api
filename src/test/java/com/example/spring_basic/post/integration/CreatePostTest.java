package com.example.spring_basic.post.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreatePostTest {
    @Test
    @DisplayName("로그인하지 않은 사용자는 게시글 생성에 실패한다.")
    void create_post_with_unauthorized_user_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("잘못된 파라미터로 게시글 생성에 실패한다.")
    void create_post_with_bad_request_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("로그인한 사용자는 게시글을 생성할 수 있다.")
    void create_post_success_test() {
        // given
        // when
        // then
    }
}
