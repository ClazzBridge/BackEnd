package com.example.academy.dto.post;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostCreateDTO {

    private String title;
    private String content;
    private Long memberId;
    private Long boardId;
    private Long classroomId;
}
