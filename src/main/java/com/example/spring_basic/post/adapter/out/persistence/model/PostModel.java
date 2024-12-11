package com.example.spring_basic.post.adapter.out.persistence.model;

import com.example.spring_basic.user.adapter.out.persistence.model.UserModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)  // JPA 감사 리스너 추가
@Table(name = "POST")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "TITLE", nullable = false)
    private String title;

    @NotNull
    private String content;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)  // ManyToOne은 기본적으로 EAGER 전략이다.
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private UserModel user;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public void setContent(@NotNull String content) {
        this.content = content;
    }

}
