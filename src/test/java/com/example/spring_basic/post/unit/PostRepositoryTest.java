package com.example.spring_basic.post.unit;

import com.example.spring_basic.global.error.exception.forbidden.ForbiddenException;
import com.example.spring_basic.post.adapter.out.persistence.mapper.JpaPostMapper;
import com.example.spring_basic.post.adapter.out.persistence.model.PostModel;
import com.example.spring_basic.post.adapter.out.persistence.repository.JpaPostRepository;
import com.example.spring_basic.post.adapter.out.persistence.repository.PostRepositoryImpl;
import com.example.spring_basic.post.domain.entity.Post;
import com.example.spring_basic.post.domain.port.out.repository.PostRepository;
import com.example.spring_basic.user.adapter.out.persistence.mapper.JpaUserMapper;
import com.example.spring_basic.user.adapter.out.persistence.model.UserModel;
import com.example.spring_basic.user.adapter.out.persistence.repository.JpaUserRepository;
import com.example.spring_basic.user.domain.vo.AccountStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


// @DataJpaTest  // jpa 관련 빈만 등록. @Transactional이 적용되어 있어서 테스트 메서드가 끝나면 롤백한다.
// @Import(JpaUserMapper.class)  // JPA와 관련 없는 빈은 Import를 사용하여 등록
@SpringBootTest
public class PostRepositoryTest {
    private UserModel user1;
    private PostModel post1;
    private PostModel post2;
    private PostRepository postRepository;

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRepository jpaUserRepository;

    @Autowired  // 생성자 주입을 사용하는 이유: 필수적인 의존성 주입을 보장하기 위해
    public PostRepositoryTest(JpaPostRepository jpaPostRepository, JpaUserRepository jpaUserRepository, JpaUserMapper userMapper) {
        this.jpaPostRepository = jpaPostRepository;
        this.jpaUserRepository = jpaUserRepository;
        JpaPostMapper postMapper = new JpaPostMapper(userMapper);
        this.postRepository = new PostRepositoryImpl(jpaPostRepository, postMapper);
    }

    @BeforeEach
    void setUp() {
        user1 = UserModel.builder()
                .username("user1")
                .password("password")
                .email("user1@test.com")
                .status(AccountStatus.ACTIVE)
                .build();

        jpaUserRepository.save(user1);
        user1 = jpaUserRepository.findById(user1.getId()).orElseThrow();

        post1 = PostModel.builder()
                .title("title1")
                .content("content1")
                .user(user1)
                .build();

        post2 = PostModel.builder()
                .title("title2")
                .content("content2")
                .user(user1)
                .build();
        jpaPostRepository.saveAll(List.of(post1, post2));
    }

    @AfterEach
    void tearDown() {
        jpaPostRepository.deleteAll();
        jpaUserRepository.deleteAll();
    }

    @DisplayName("게시글 전체 조회")
    @Test
    @Transactional
    void findAllTest() {
        // given
        // when
        List<Post> all = postRepository.findAll();

        // then
        assertThat(all).hasSize(2)
                .extracting("id")
                .containsExactlyInAnyOrder(post1.getId(), post2.getId());
    }

    @DisplayName("게시글 id로 게시글 상세 조회")
    @Test
    @Transactional
    void findByIdTest() {
        // given
        // when
        Optional<Post> find = postRepository.findById(post1.getId());

        // then
        assertThat(find).isPresent()
                .get()
                .isInstanceOf(Post.class);
    }

    @DisplayName("존재하지 않는 게시글 id로 게시글 상세 조회")
    @Test
    @Transactional
    void findByIdFailTest() {
        // given
        // when
        Optional<Post> find = postRepository.findById(11234L);

        // then
        assertThat(find).isEmpty();
    }

    @Test
    @Transactional
    void updateTest() {
        // given
        Post post = postRepository.findById(post1.getId()).get();
        String newTitle = "title111";
        String newContent = "content111";

        Post updateInput = Post.builder()
                .id(post.getId())
                .title(newTitle)
                .content(newContent)
                .user(post.getUser())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();

        // when
        Post updated = postRepository.update(updateInput, post.getUser().getId());

        // then
        assertThat(updated.getTitle()).isEqualTo(newTitle);
        assertThat(updated.getContent()).isEqualTo(newContent);
    }

    @DisplayName("작성자가 아닌 사용자는 게시글을 수정할 수 없다.")
    @Test
    @Transactional
    void updateForbiddenTest() {
        // given
        Post post = postRepository.findById(post1.getId()).get();
        String newTitle = "title111";
        String newContent = "content111";

        Post updateInput = Post.builder()
                .id(post.getId())
                .title(newTitle)
                .content(newContent)
                .user(post.getUser())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();

        // when
        // then
        assertThatThrownBy(() -> postRepository.update(updateInput, 100L))
                .isInstanceOf(ForbiddenException.class)
                .hasMessageContaining("Access is forbidden");


    }

    @Test
    @Transactional
    void deleteTest() {
        // given

        // when
        postRepository.delete(post1.getId(), user1.getId());

        // then
        assertThat(postRepository.findById(post1.getId())).isEmpty();
    }
}