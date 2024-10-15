package com.example.academy.dto.question;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionDetailReadDTO {

  private Long id;
  private String studentName;
  private String teacherName;
  private String content;
  private boolean isRecommended;
  private Date createdAt;
  private Date answeredAt;
  private String aiAnswer;
  private String teacherAnswer;
}
