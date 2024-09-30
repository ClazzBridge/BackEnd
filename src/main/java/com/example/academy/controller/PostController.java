package com.example.academy.controller;


import com.example.academy.dto.PostDTO;
import com.example.academy.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "모든 게시글 반환", description = "모든 게시글 반환")
    @GetMapping("")
    public ResponseEntity<List<PostDTO>> findAllPosts() {
        List<PostDTO> postDTOs = postService.findAllPosts();
        return ResponseEntity.ok().body(postDTOs);
    }

    @Operation(summary = "자유게시판 게시글 반환", description = "자유게시판 게시글 반환")
    @GetMapping("/freeBoard")
    public ResponseEntity<List<PostDTO>> findAllFreePosts() {
        List<PostDTO> postDTOs = postService.findAllFreePosts();
        return ResponseEntity.ok().body(postDTOs);
    }

    @Operation(summary = "공지사항 게시글 반환", description = "공지사항 게시글 반환")
    @GetMapping("/notification")
    public ResponseEntity<List<PostDTO>> findAllNotificationPosts() {
        List<PostDTO> postDTOs = postService.findAllNotificationPosts();
        return ResponseEntity.ok().body(postDTOs);
    }

    @Operation(summary = "게시글 하나 반환", description = "ID에 맞는 게시글 하나 반환")
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable("id") Long id) {
        PostDTO postDTO = postService.findById(id);
        return ResponseEntity.ok().body(postDTO);
    }

}
