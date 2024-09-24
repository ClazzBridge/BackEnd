package com.example.academy.controller;


import com.example.academy.dto.PostDTO;
import com.example.academy.service.PostService;
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


    @GetMapping("")
    public ResponseEntity<List<PostDTO>> findAllPosts() {
        List<PostDTO> postDTOs = postService.findAllPosts();
        return ResponseEntity.ok().body(postDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable("id") Long id) {
        PostDTO postDTO = postService.findById(id);
        return ResponseEntity.ok().body(postDTO);
    }

}
