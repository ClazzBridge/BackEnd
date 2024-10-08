package com.example.academy.dto.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionToggleRecommendedDTO {

  private Long id;
  private boolean isRecommended;
}
