package com.example.academy.dto.vote;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class GetVoteDTO {
  private String title;
  private String description;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate endDate;
  private Boolean isExpired;

  public GetVoteDTO(String title, String description, LocalDate startDate, LocalDate endDate, Boolean isExpired) {
    this.title = title;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.isExpired = isExpired;
  }
}
