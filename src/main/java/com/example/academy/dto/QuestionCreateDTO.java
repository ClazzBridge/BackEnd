package com.example.academy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionCreateDTO {

  private String content;
  private Long userId;

  public QuestionCreateDTO(String content, Long userId) {
    this.content = content;
    this.userId = userId;
  }

}
