package com.example.academy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerCreateDTO {

  private String content;
  private Long userId;

  public AnswerCreateDTO(String content, Long userId) {
    this.content = content;
    this.userId = userId;
  }

}
