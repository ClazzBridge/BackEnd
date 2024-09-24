package com.example.academy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ScheduleAddDTO {

  private Long classroomId; // 강의실 ID

  private String eventTitle; // 일정 제목

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime startDate; // 일정 시작 날짜
  
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime endDate; // 일정 종료 날짜

  private String description; // 일정 설명

}
