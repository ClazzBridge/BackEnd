package com.example.academy.domain.mysql;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
  @Column(name = "id", nullable = false)
  private Integer id;

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
  private Instant createdAt;

  @Lob
  @Column(name = "ai_answer")
  private String aiAnswer;

  @Lob
  @Column(name = "teacher_answer")
  private String teacherAnswer;

  @Column(name = "answered_at")
  private Instant answeredAt;

  @Column(name = "is_recommended")
  private Boolean isRecommended;

}