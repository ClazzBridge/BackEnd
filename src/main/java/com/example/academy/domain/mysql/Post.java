package com.example.academy.domain.mysql;

import com.example.academy.common.BaseTimeEntity;
import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "post")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Member author;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;


    public Post() {
    }

    @Builder
    public Post(Long id, Board board, Course course, Member author, String title,
        String content) {
        this.id = id;
        this.board = board;
        this.course = course;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    @Builder
    public Post(Board board, Course course, Member author, String title,
        String content) {
        this.board = board;
        this.course = course;
        this.author = author;
        this.title = title;
        this.content = content;
    }


    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }


}