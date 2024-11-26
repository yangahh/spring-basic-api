package com.example.spring_basic.user.adapter.out.persistence.model;

import com.example.spring_basic.user.domain.vo.AccountStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "USERNAME", unique = true)  // nullable = false는 @NotNull로 대체 가능
    private String username;  // 로그인 id

    @NotNull
    private String password;

    @NotNull
    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "NICK_NAME")
    private String nickname;

    @Column(name = "STATUS", nullable = false)
    @ColumnDefault("'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "IS_STAFF", nullable = false, columnDefinition = "TINYINT(1)")
    @ColumnDefault("0")
    private boolean isStaff;

    @Column(name = "LAST_LOGIN_AT")
    private LocalDateTime lastLoginAt;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}
