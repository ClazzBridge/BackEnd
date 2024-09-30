package com.example.academy.mapper;

import com.example.academy.domain.Post;
import com.example.academy.dto.PostDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostDTO toDto(Post post) {
        return new PostDTO(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getAuthor().getName(),
            post.getBoard().getBoardType().getDescription(),
            post.getClassroom().getName(),
            post.getCreatedAt()
        );
    }

    public List<PostDTO> toDtoList(List<Post> posts) {
        return posts.stream()
            .map(todo -> toDto(todo))
            .collect(Collectors.toList());
    }
}
