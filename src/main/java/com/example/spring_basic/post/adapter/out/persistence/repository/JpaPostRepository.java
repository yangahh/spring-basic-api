package com.example.spring_basic.post.adapter.out.persistence.repository;

import com.example.spring_basic.post.adapter.out.persistence.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPostRepository extends JpaRepository<PostModel, Long> {
}
