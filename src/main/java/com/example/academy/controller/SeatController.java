package com.example.academy.controller;

import com.example.academy.domain.Member;
import com.example.academy.domain.Seat;
import com.example.academy.dto.SeatDTO;
import com.example.academy.service.SeatService;
import com.example.academy.service.MemberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
  private final MemberService memberService; // UserService 주입 필요

  @Autowired
  public SeatController(SeatService seatService, MemberService memberService) {
    this.seatService = seatService;
    this.memberService = memberService; // UserService 초기화
  }

  //좌석

  @GetMapping("/")
  public ResponseEntity<List<Seat>> getAllSeat() {
    List<Seat> listSeat = seatService.getAllSeat();
    return ResponseEntity.ok(listSeat);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Seat> updateSeat(@PathVariable Long id, @RequestBody SeatDTO seatDTO) {
    Seat existingSeat = seatService.updateSeat(id, seatDTO);
    return ResponseEntity.ok(existingSeat);
  }

  @PostMapping("/")
  public ResponseEntity<Seat> createSeat(@RequestBody SeatDTO seatDTO) {
    Seat newSeat = seatService.createSeat(seatDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(newSeat);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSeat(@PathVariable Long id) {
    seatService.deleteSeat(id);
    return ResponseEntity.noContent().build();
  }
}
