package com.example.academy.controller;

import com.example.academy.domain.Member;
import com.example.academy.domain.Seat;
import com.example.academy.dto.SeatDTO;
import com.example.academy.service.SeatService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seat")
public class SeatController {

  private final SeatService seatService;

  @Autowired
  public SeatController(SeatService seatService) {
    this.seatService = seatService;
  }

  //좌석

  @GetMapping("/")
  public ResponseEntity<List<SeatDTO>> getAllSeat() {
    List<SeatDTO> seatDTOList = seatService.getAllSeat();
    return ResponseEntity.ok(seatDTOList);
  }


  @PutMapping("/")
  public ResponseEntity<SeatDTO> assignMemberToSeat(@RequestBody SeatDTO seatDTO) {
    if (seatDTO.getMemberDTO() != null && seatDTO.getId() != null) {
      SeatDTO updatedSeatDTO = seatService.assignMemberToSeatById(seatDTO.getId(), seatDTO.getMemberDTO().getMemberId());
      return ResponseEntity.ok(updatedSeatDTO);
    } else {
      return ResponseEntity.badRequest().build(); // 멤버 정보나 좌석 ID가 없으면 잘못된 요청 처리
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Seat> removeMemberFromSeat(@PathVariable Long id) {
    Seat updatedSeat = seatService.removeMemberFromSeat(id);
    return ResponseEntity.ok(updatedSeat);
  }
}
