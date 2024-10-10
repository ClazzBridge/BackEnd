package com.example.academy.dto.seat;

import com.example.academy.dto.member.MemberDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDTO {

  private Long id;
  private String seatNumber;
  private Boolean isExist = true;
  private Boolean isOnline = false;
  private MemberDTO memberDTO;

  public SeatDTO(Long id, String seatNumber, Boolean isExist, Boolean isOnline, MemberDTO memberDTO) {
    this.id = id;
    this.seatNumber = seatNumber;
    this.isExist = isExist;
    this.isOnline = isOnline;
    this.memberDTO = memberDTO;
  }
}


