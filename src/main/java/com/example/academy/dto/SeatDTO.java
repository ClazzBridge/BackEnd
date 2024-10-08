package com.example.academy.dto;

import com.example.academy.domain.Classroom;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDTO {

  private Long id;
  private Classroom classroom;
  private String seatNumber;
  private Boolean isExist = true;
  private Boolean isOnline = false;
  private MemberDTO memberDTO;

  public SeatDTO(Long id, Classroom classroom, String seatNumber, Boolean isExist, Boolean isOnline, MemberDTO memberDTO) {
    this.id = id;
    this.classroom = classroom;
    this.seatNumber = seatNumber;
    this.isExist = isExist;
    this.isOnline = isOnline;
    this.memberDTO = memberDTO;
  }
}


