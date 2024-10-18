package com.example.academy.dto.seat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatUpdateDTO {

  private Long id;
  private String seatNumber;
private Long memberId;
private Long courseId;

public SeatUpdateDTO(Long id, String seatNumber, Long memberId, Long courseId) {
  this.id = id;
  this.seatNumber = seatNumber;
  this.memberId = memberId;
  this.courseId = courseId;
}
}


