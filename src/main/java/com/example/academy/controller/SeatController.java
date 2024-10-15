package com.example.academy.controller;


import com.example.academy.dto.seat.SeatDTO;
import com.example.academy.service.SeatService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seat")
public class SeatController {

  private final SeatService seatService;

  @Autowired
  public SeatController(SeatService seatService) {
    this.seatService = seatService;
  }

  @Operation(summary = "모든 좌석 리스트 반환")
  @GetMapping("/")
  public ResponseEntity<List<SeatDTO>> getAllSeat() {
    List<SeatDTO> seatDTOList = seatService.getAllSeat();
    return ResponseEntity.ok(seatDTOList);
  }

  @Operation(summary = "멤버 등록", description = "특정 좌석에 멤버 등록")
  @PutMapping("/assign")
  public ResponseEntity<?> assignSeat(@RequestBody SeatDTO seatDTO) {
    Optional<SeatDTO> result = seatService.assignSeatToMember(seatDTO); // seatDTO 전체를 전달
    return result.map(seat -> ResponseEntity.ok("좌석이 성공적으로 배정되었습니다."))
        .orElse(ResponseEntity.status(HttpStatus.CONFLICT)
            .body("이 회원은 이미 다른 좌석을 점유하고 있습니다."));
  }

  @Operation(summary = "멤버 해제", description = "특정 좌석 멤버 해제")
  @DeleteMapping("/{id}")
  public ResponseEntity<SeatDTO> removeMemberFromSeat(@PathVariable Long id) {
    SeatDTO updatedSeat = seatService.removeMemberFromSeat(id);
    return ResponseEntity.ok(updatedSeat);
  }

  @Operation(summary = "좌석 등록", description = "ADMIN 사용자가 지정된 수의 좌석을 등록")
  @PostMapping("/register")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> registerSeats(@RequestBody SeatRegistrationRequest request) {
    try {
      List<SeatDTO> createdSeats = seatService.registerSeats(request.getSeatCount());
      return ResponseEntity.ok(createdSeats);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Operation(summary = "좌석 수정", description = "ADMIN 사용자가 좌석 수를 수정")
  @PostMapping("/modify")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> modifySeats(@RequestBody SeatModificationRequest request) {
    try {
      List<SeatDTO> modifiedSeats = seatService.modifySeats(request.getSeatCount());
      return ResponseEntity.ok(modifiedSeats);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Operation(summary = "강사 좌석 배정", description = "강사를 고정 좌석에 배정")
  @PutMapping("/assignTeacher/{memberId}")
  public ResponseEntity<?> assignTeacherSeat(@PathVariable String memberId) {
    try {
      SeatDTO assignedSeat = seatService.assignTeacherSeat(memberId);
      return ResponseEntity.ok(assignedSeat);
    } catch (IllegalStateException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}


class SeatRegistrationRequest {

  private int seatCount;

  public int getSeatCount() {
    return seatCount;
  }

  public void setSeatCount(int seatCount) {
    this.seatCount = seatCount;
  }
}

  class SeatModificationRequest {
    private int seatCount;

    public int getSeatCount() {
      return seatCount;
    }

    public void setSeatCount(int seatCount) {
      this.seatCount = seatCount;
    }
}
