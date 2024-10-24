package com.example.academy.dto.vote;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class AddVoteDTO {
  private String title;
  private String description;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime startDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime endDate;

//  @Schema(description = "강의 ID 번호", example = "2")
//  private Long courseId;

  public AddVoteDTO(String title, String description, LocalDateTime startDate, LocalDateTime endDate) {
    this.title = title;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
