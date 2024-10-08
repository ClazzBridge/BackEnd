package com.example.academy.dto.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionToggleSolvedDTO {

  private Long id;
  private boolean isSolved;
}
