package com.example.academy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionUpdateDTO {

  private String content;

  public QuestionUpdateDTO(String content) {
    this.content = content;
  }

}
