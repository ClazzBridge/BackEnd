package com.example.academy.domain.mysql;

import javax.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 게시판 고유 식별자 (Primary Key)

    @ManyToOne
    @JoinColumn(name = "board_type_id", nullable = false)
    private BoardType boardType;  // 게시판 유형 (Foreign Key)

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BoardType getBoardType() {
        return boardType;
    }

    public void setBoardType(BoardType boardType) {
        this.boardType = boardType;
    }
}