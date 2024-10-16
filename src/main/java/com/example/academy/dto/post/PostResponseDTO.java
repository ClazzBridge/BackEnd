package com.example.academy.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostResponseDTO {

    private Long id;
    private String title;
    private String content;
    private String authorName;
    private String boardType;
    private String courseTitle;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    public PostResponseDTO() {
    }

    public PostResponseDTO(Long id, String title, String content, String authorName,
        String boardType,
        String courseTitle, Date createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.boardType = boardType;
        this.courseTitle = courseTitle;
        this.createdAt = createdAt;
    }

}
