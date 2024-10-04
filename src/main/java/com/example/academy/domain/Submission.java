package com.example.academy.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "submission")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Member student;

    private String submissionUrl;

    @Temporal(TemporalType.DATE)
    private Date submissionDate;

    private Double grade;

    // Getters and Setters
}