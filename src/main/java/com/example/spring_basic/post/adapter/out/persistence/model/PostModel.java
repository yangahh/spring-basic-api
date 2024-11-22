package com.example.spring_basic.post.adapter.out.persistence.model;

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
@NoArgsConstructor
@Builder
@Getter
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    // TODO: Change to User Model
    @NotNull
    private String author;

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
