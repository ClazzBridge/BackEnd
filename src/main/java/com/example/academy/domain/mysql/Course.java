package com.example.academy.domain.mysql;

import javax.persistence.*;
import java.util.Date;
import lombok.Getter;

@Entity
@Getter
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Member instructor;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

}