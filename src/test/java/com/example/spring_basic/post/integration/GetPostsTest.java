package com.example.spring_basic.post.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetPostsTest {

    @Test
    @DisplayName("로그인 하지 않은 사용자는 게시글 전체 조회를 할 수 있다.")
    void get_posts_with_not_login_user_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("로그인 한 사용자는 게시글 전체 조회를 할 수 있다.")
    void get_posts_with_login_user_test() {
        // given
        // when
        // then
    }
}
