package com.example.academy.domain.mysql;

import com.example.academy.common.BaseTimeEntity;
import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Member author;

    @Column(nullable = false)
    private String content;

    // Getters and Setters

    public String updateContent(String newContent) {
        this.content = newContent;
        return this.content;
    }
}