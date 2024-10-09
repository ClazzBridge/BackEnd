package com.example.academy.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Schedule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // 스케쥴 고유 식별자

  @Column(name = "course_Id", nullable = false)
  private Long courseId; // 강의실 ID

  @Column(name = "event_title", nullable = false)
  private String eventTitle; // 일정 제목

  @Column(name = "start_date", columnDefinition = "TIMESTAMP")
  private LocalDateTime startDate; // 일정 시작 날짜

  @Column(name = "end_date", columnDefinition = "TIMESTAMP")
  private LocalDateTime endDate; // 일정 종료 날짜

  @Column(name = "description")
  private String description; // 일정 설명

}
