package com.example.academy.service;

import com.example.academy.domain.PostList;
import com.example.academy.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Optional<PostList> getPostListByPostId(Long postId) {
        return postRepository.findById(postId);

    }

    public List<PostList> getAll(){
        return postRepository.findAll();
    }
}
