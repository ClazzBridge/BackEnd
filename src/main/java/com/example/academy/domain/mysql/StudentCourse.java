package com.example.academy.domain.mysql;

import com.example.academy.domain.mysql.Course;
import com.example.academy.domain.mysql.Member;
import javax.persistence.*;
import java.util.Date;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
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