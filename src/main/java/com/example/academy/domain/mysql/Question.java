package com.example.academy.domain.mysql;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "question")
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "student_course_id", nullable = false)
  private StudentCourse studentCourse;

  @NotNull
  @Lob
  @Column(name = "content", nullable = false)
  private String content;

  @NotNull
  @Column(name = "created_at", nullable = false)
  private Date createdAt;

  @Lob
  @Column(name = "ai_answer")
  private String aiAnswer;

  @Lob
  @Column(name = "teacher_answer")
  private String teacherAnswer;

  @Column(name = "answered_at")
  private Date answeredAt;

  @Column(name = "is_recommended")
  private boolean isRecommended;

}