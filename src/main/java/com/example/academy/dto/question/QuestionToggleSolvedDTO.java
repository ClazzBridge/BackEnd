package com.example.academy.dto.question;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionToggleSolvedDTO {

  private Long id;
  private boolean isSolved;
}
