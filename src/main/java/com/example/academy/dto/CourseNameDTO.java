package com.example.academy.dto;

import lombok.Data;

@Data
public class CourseNameDTO {

  private String CourseName;

  public CourseNameDTO(String courseName) {
    CourseName = courseName;
  }
}
