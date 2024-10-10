package com.example.academy.controller;

import com.example.academy.dto.seat.SeatDTO;
import com.example.academy.service.SeatService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  @PutMapping("/{seatId}/{memberId}")
  public ResponseEntity<?> assignSeat(@PathVariable Long seatId, @PathVariable String memberId) {
    Optional<SeatDTO> result = seatService.assignSeatToMember(seatId, memberId);
    return result.map(seatDTO -> ResponseEntity.ok("좌석이 성공적으로 배정되었습니다."))
        .orElse(ResponseEntity.status(HttpStatus.CONFLICT)
            .body("이 회원은 이미 다른 좌석을 점유하고 있습니다."));
  }

  @Operation(summary = "멤버 해제", description = "특정 좌석 멤버 해제")
  @DeleteMapping("/{id}")
  public ResponseEntity<SeatDTO> removeMemberFromSeat(@PathVariable Long id) {
    SeatDTO updatedSeat = seatService.removeMemberFromSeat(id);
    return ResponseEntity.ok(updatedSeat);
  }
}