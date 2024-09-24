package com.example.academy.service;

import com.example.academy.domain.Post;
import com.example.academy.dto.PostDTO;
import com.example.academy.mapper.PostMapper;
import com.example.academy.repository.PostRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public PostDTO findById(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 없습니다."));
        return postMapper.toDto(post);
    }

    public List<PostDTO> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return postMapper.toDtoList(posts);
    }
}
