package com.example.academy.controller;


import com.example.academy.domain.PostList;
import com.example.academy.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostList> getPostList() {

        List<PostList> postList = postService.getAll();
        System.out.println(postList);
        return postList;
    }
}
