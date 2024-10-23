package com.example.academy.dto.vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class GetVoteDTO {

  private Long id;
  private String title;
  private String courseTitle;
  private String description;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Boolean isExpired;

  public GetVoteDTO() {
  }

  public GetVoteDTO(Long id, String title, String courseTitle, String description, LocalDateTime startDate, LocalDateTime endDate, Boolean isExpired) {
    this.id = id;
    this.title = title;
    this.courseTitle = courseTitle;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.isExpired = isExpired;
  }
}
