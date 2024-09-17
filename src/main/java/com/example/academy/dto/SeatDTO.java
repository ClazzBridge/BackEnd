package com.example.academy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDTO {

  private String seatNumber;
  private Long userId;

  public SeatDTO(String seatNumber, Long userId) {
    this.seatNumber = seatNumber;
    this.userId = userId;
  }
}


