package com.example.academy.dto.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionCreateDTO {

  private String content;
  private Long memberId;

}
