package com.example.academy.dto.vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class AddVoteDTO {
  private String title;
  private String courseTitle;
  private String description;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime startDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime endDate;

  public AddVoteDTO(String title, String courseTitle, String description, LocalDateTime startDate, LocalDateTime endDate) {
    this.title = title;
    this.courseTitle = courseTitle;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
