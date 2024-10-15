package com.example.academy.mapper.post;

import com.example.academy.domain.mysql.BoardType;
import com.example.academy.domain.mysql.Course;
import com.example.academy.domain.mysql.Member;
import com.example.academy.domain.mysql.Post;
import com.example.academy.dto.post.PostCreateDTO;
import org.springframework.stereotype.Component;

@Component
public class PostCreateMapper {

    public Post toEntity(PostCreateDTO postDTO, Member member, BoardType boardType, Course course) {
        return Post.builder()
            .boardType(boardType)
            .author(member)
            .course(course)
            .title(postDTO.getTitle())
            .content(postDTO.getContent())
            .build();
    }

//    public List<Post> toEntityList(List<PostCreateDTO> postDTOList) {
//        if (postDTOList == null) {
//            return null;
//        }
//        return postDTOList.stream()
//            .map(dto -> toEntity(dto))
//            .collect(Collectors.toList());
//    }

}