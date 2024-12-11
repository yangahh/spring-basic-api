package com.example.spring_basic.post.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeletePostTest {
    @Test
    @DisplayName("로그인하지 않은 사용자는 게시글을 삭제할 수 없다.")
    void delete_post_with_unauthorized_user_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("다른 사용자의 게시글은 삭제할 수 없다.")
    void deletePostForbiddenTest() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("존재하지 않는 게시글은 삭제에 실패한다.")
    void deletePostNotFoundTest() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("로그인한 사용자는 자신이 작성한 게시글을 삭제할 수 있다.")
    void delete_post_success_test() {
        // given
        // when
        // then
    }
}
