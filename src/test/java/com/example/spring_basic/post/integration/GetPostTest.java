package com.example.spring_basic.post.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetPostTest {
    @Test
    @DisplayName("로그인하지 않은 사용자는 게시글을 상세 조회 할 수 있다.")
    void get_post_with_not_login_user_test() {
        // given
        // when
        // then
    }
    
    @Test
    @DisplayName("로그인한 사용자는 게시글을 상세 조회 할 수 있다.")
    void get_post_with_login_user_test() {
        // given
        // when
        // then
    }
}
