package com.example.academy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionDTO {

  private String content;
  private Long id;

  public CreateQuestionDTO(String content, Long id) {
    this.content = content;
    this.id = id;
  }

}
