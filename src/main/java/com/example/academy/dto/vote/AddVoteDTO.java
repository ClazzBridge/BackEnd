package com.example.academy.dto.vote;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class AddVoteDTO {
  private String title;
  private String description;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate endDate;

  public AddVoteDTO(String title, String description, LocalDate startDate, LocalDate endDate) {
    this.title = title;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
