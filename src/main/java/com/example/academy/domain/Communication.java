package com.example.academy.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "communication")
public class Communication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Member sender;

    @Column(nullable = false)
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // Getters and Setters
}