package com.example.academy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDTO {

  private String seatNumber;
  private Long memberId;

  public SeatDTO(String seatNumber, Long memberId) {
    this.seatNumber = seatNumber;
    this.memberId = memberId;
  }
}


