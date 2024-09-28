package com.example.academy.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Student_Course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourse {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private UserList student;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  @CreationTimestamp
  @Column(nullable = false)
  private Date enrollmentDate;

}
