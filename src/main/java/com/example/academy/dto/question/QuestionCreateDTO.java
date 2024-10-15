package com.example.academy.dto.question;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionCreateDTO {

  private String content;
  private Long memberId;
  private Long courseId;

}
