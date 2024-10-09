package com.example.academy.mapper.post;

import com.example.academy.domain.mysql.Board;
import com.example.academy.domain.mysql.Classroom;
import com.example.academy.domain.mysql.Member;
import com.example.academy.domain.mysql.Post;
import com.example.academy.dto.post.PostCreateDTO;
import org.springframework.stereotype.Component;

@Component
public class PostCreateMapper {

    public Post toEntity(PostCreateDTO postDTO, Member member, Board board, Classroom classroom) {
        return Post.builder()
            .board(board)
            .author(member)
            .classroom(classroom)
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
