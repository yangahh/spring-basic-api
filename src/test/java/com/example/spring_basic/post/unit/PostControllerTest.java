package com.example.spring_basic.post.unit;

import com.example.spring_basic.post.adapter.in.web.controller.PostController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(PostController.class)
public class PostControllerTest {
    @Test
    @DisplayName("필수 파라미터를 누락한 경우에 게시글을 생성할 수 없다.")
    void create_post_without_required_parameter_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("잘못된 파라미터 이름으로 게시글을 생성할 수 없다.")
    void create_post_with_bad_request_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("게시글 제목이 빈 값인 경우 게시글을 생성할 수 없다.")
    void create_post_with_empty_title_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("올바른 파라미터로 게시글을 생성할 수 있다.")
    void create_post_success_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("게시글 전체 조회 성공")
    void get_posts_success_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("잘못된 형식의 게시글 id로 게시글 상세 조회를 할 수 없다.")
    void get_post_with_wrong_post_id_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("아무 파라미터를 입력하지 않은 경우에 게시글 수정에 실패하지 않는다.")
    void update_post_without_parameter_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("잘못된 파라미터 이름으로 게시글을 수정할 수 없다.")
    void update_post_with_bad_request_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("게시글 제목이 빈 값인 경우 게시글을 수정할 수 없다.")
    void update_post_with_empty_title_test() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("올바른 파라미터로 게시글 수정에 성공한다.")
    void update_post_success_test() {
        // given
        // when
        // then
    }

}