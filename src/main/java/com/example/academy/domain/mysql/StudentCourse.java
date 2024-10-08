package com.example.academy.domain.mysql;

import javax.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "student_course")
public class StudentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Member student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}