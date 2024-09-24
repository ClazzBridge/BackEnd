package com.example.academy.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Temporal(TemporalType.DATE)
    private Date createDate;

    private boolean answerComplete;

    // Getters and Setters
}