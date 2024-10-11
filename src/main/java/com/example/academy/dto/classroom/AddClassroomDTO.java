package com.example.academy.dto.classroom;

import lombok.Data;

@Data
public class AddClassroomDTO {

  private String name; //강의실 명

  private int capacity; // 수용 인원

  public AddClassroomDTO(String name, int capacity) {
    this.name = name;
    this.capacity = capacity;
  }
}
