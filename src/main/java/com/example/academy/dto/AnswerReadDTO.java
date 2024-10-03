package com.example.academy.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerReadDTO {

  private Long id;
  private String userName;
  private String content;
  private Date createdAt;
  private Date updatedAt;

}
