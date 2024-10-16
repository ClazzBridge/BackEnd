package com.example.academy.dto.question;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeacherAnswerCreateDTO {

  private Long questionId;
  private String teacherAnswer;
}
