package com.example.academy.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@Entity
public class PostList {


    //고유 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column( length = 100)
    private String title;  // 게시물 제목

    @Column(columnDefinition = "TEXT")
    private String content; // 내용

    @Column( length = 100)
    private String name;  // 작성자 이름

    @Column
    private LocalDateTime createAt; // 작성 시간

    // 엔티티가 persist 될 때 작성 시간을 자동으로 설정하는 메서드
    @PrePersist
    protected void onCreate() {
        this.createAt = LocalDateTime.now();
    }
}
