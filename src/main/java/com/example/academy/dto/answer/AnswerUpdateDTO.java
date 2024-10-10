package com.example.academy.dto.answer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerUpdateDTO {

  private Long id;
  private String content;

}
