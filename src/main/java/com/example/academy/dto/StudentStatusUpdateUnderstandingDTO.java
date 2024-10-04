package com.example.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentStatusUpdateUnderstandingDTO {

  private Long memberId;
  private boolean isUnderstanding;
}
