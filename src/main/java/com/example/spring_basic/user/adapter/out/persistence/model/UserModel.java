package com.example.spring_basic.user.adapter.out.persistence.model;

import com.example.spring_basic.user.domain.vo.AccountStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "USERS")  // 예약어 때문에 s를 붙여줌
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private AccountStatus status = AccountStatus.ACTIVE; // 기본값 설정

    @Column(name = "IS_STAFF", nullable = false)
    @ColumnDefault("0")
    private boolean isStaff = false;  // 기본값 설정

    @Column(name = "LAST_LOGIN_AT")
    private LocalDateTime lastLoginAt;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}
