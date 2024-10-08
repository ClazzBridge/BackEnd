package com.example.academy.dto.question;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionReadDTO {

  private Long id;
  private String memberName;
  private String content;
  private boolean isSolved;
  private boolean isRecommended;
  private Date createdAt;
  private Date updatedAt;
}
