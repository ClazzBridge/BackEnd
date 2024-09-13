package com.example.academy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerUpdateDTO {

  private String content;

  public AnswerUpdateDTO(String content) {
    this.content = content;
  }

}
