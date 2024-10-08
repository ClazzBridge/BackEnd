package com.example.academy.dto.answer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerCreateDTO {

  private String content;
  private Long memberId;
  private Long questionId;

}
