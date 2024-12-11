package com.example.spring_basic.post.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdatePostTest {
    @Test
    @DisplayName("로그인하지 않은 사용자는 게시글을 수정할 수 없다.")
    void update_post_with_unauthorized_user_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("다른 사용자의 게시글은 수정할 수 없다.")
    void update_post_forbidden_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("존재하지 않는 게시글은 수정에 실패한다.")
    void update_post_not_found_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("게시글 수정 실패 테스트: 잘못된 파라미터")
    void update_post_with_bad_request_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("로그인 한 사용자는 자신이 작성한 게시글을 수정할 수 있다.")
    void update_post_success_test() {
        // given
        // when
        // then
    }
}
