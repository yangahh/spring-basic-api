package com.example.spring_basic.post.adapter.in.web.controller;

import com.example.spring_basic.post.adapter.in.web.dto.PostCreationRequestDto;
import com.example.spring_basic.post.adapter.in.web.dto.PostResponseDto;
import com.example.spring_basic.post.adapter.in.web.dto.PostUpdateRequestDto;
import com.example.spring_basic.post.adapter.in.web.mapper.PostMapper;
import com.example.spring_basic.post.domain.entity.Post;
import com.example.spring_basic.post.domain.port.in.usecase.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final CreatePostUsecase createPostUsecase;
    private final GetPostsUsecase getPostsUsecase;
    private final GetPostUsecase getPostUsecase;
    private final UpdatePostUsecase updatePostUsecase;
    private final DeletePostUsecase deletePostUsecase;
    private final PostMapper mapper;

    @PostMapping
    public ResponseEntity<String> createPost(@Valid @RequestBody PostCreationRequestDto requestDto) {
        createPostUsecase.execute(mapper.mapToDomainDtoFromCreationRequest(requestDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        List<PostResponseDto> res = getPostsUsecase.execute().stream()
                .map(mapper::mapToResponseDto)
                .toList();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable("id") Long id) {
        PostResponseDto res = mapper.mapToResponseDto(getPostUsecase.execute(id));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable("id") Long id, @Valid @RequestBody PostUpdateRequestDto requestDto) {
        Post updated = updatePostUsecase.execute(mapper.mapToDomainEntityFromUpdateRequest(id, requestDto));
        PostResponseDto res = mapper.mapToResponseDto(updated);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        deletePostUsecase.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
