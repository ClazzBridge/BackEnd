package com.example.academy.domain.mysql;

import javax.persistence.*;
import lombok.Getter;

@Getter

@Entity
@Table(name = "board_type")
public class BoardType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 게시판 유형 고유 식별자 (Primary Key)

    @Column(nullable = false, length = 100)
    private String description;  // 게시판 설명

    // getters and setters
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
