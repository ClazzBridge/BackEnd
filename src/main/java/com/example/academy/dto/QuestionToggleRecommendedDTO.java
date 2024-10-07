package com.example.academy.dto;

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
