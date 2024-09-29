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

  @Column(name = "classroom_id", nullable = false)
  private Long classroomId; // 강의실 ID

  @Column(name = "event_title", nullable = false, length = 100)
  private String eventTitle; // 일정 제목

  @Column(name = "start_date", columnDefinition = "TIMESTAMP")
  private LocalDateTime startDate; // 일정 시작 날짜

  @Column(name = "end_date", columnDefinition = "TIMESTAMP")
  private LocalDateTime endDate; // 일정 종료 날짜

  @Column(name = "description")
  private String description; // 일정 설명

}
