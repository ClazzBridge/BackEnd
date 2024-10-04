package com.example.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerCreateDTO {

  private String content;
  private Long userId;
  private Long questionId;

}
