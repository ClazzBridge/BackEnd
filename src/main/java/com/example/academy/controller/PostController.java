package com.example.academy.controller;


import com.example.academy.dto.post.PostCreateDTO;
import com.example.academy.dto.post.PostResponseDTO;
import com.example.academy.dto.post.PostUpdateDTO;
import com.example.academy.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "모든 게시글 리스트 반환")
    @GetMapping("")
    public ResponseEntity<List<PostResponseDTO>> findAllPosts() {
        List<PostResponseDTO> postDTOs = postService.findAllPosts();
        return ResponseEntity.ok().body(postDTOs);
    }

    @Operation(summary = "자유게시판 게시글 리스트 반환")
    @GetMapping("/freeBoard")
    public ResponseEntity<List<PostResponseDTO>> findAllFreePosts() {
        List<PostResponseDTO> postDTOs = postService.findAllFreePosts();
        return ResponseEntity.ok().body(postDTOs);
    }

    @Operation(summary = "공지사항 게시글 리스트 반환")
    @GetMapping("/notification")
    public ResponseEntity<List<PostResponseDTO>> findAllNotificationPosts() {
        List<PostResponseDTO> postDTOs = postService.findAllNotificationPosts();
        return ResponseEntity.ok().body(postDTOs);
    }

    @Operation(summary = "게시글 저장")
    @PostMapping("/")
    public ResponseEntity<PostResponseDTO> save(@RequestBody PostCreateDTO postDTO) {
        PostResponseDTO savedPost = postService.save(postDTO);
        return ResponseEntity.ok().body(savedPost);
    }

    @Operation(summary = "게시글 하나 반환")
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> findById(@PathVariable(value = "id") Long id) {
        PostResponseDTO postDTO = postService.findById(id);
        return ResponseEntity.ok().body(postDTO);
    }

    @Operation(summary = "게시글 수정")
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> update(@RequestBody PostUpdateDTO postDTO,
        @PathVariable(value = "id") Long id) {
        PostResponseDTO updatedPost = postService.update(id, postDTO);

        return ResponseEntity.ok().body(updatedPost);
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable(value = "id") Long id) {
        postService.delete(id);
        return HttpStatus.OK;
    }

}
