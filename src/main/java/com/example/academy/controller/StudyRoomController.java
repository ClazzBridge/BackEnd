package com.example.academy.controller;

import com.example.academy.domain.ProfileImage;
import com.example.academy.domain.Seat;
import com.example.academy.domain.User;
import com.example.academy.dto.ProfileImgDTO;
import com.example.academy.dto.SeatDTO;
import com.example.academy.service.ProfileImgService;
import com.example.academy.service.SeatService;
import com.example.academy.service.UserService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/studyroom")
public class StudyRoomController {

  private UserService userService;
  private ProfileImgService profileImgService;
  private SeatService seatService;

  @Autowired
  public StudyRoomController(UserService userService, ProfileImgService profileImgService,
      SeatService seatService) {
    this.userService = userService;
    this.profileImgService = profileImgService;
    this.seatService = seatService;
  }

  // 프로필 이미지 조회 / 변경 / 삭제 ㅇ
  // 좌석 등록 / 조회 / 변경
  // 프로필 모달창 유저 정보 출력 ㅇ

// 유저 정보 출력
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User user = userService.getUserById(id);
    return ResponseEntity.ok(user);
  }

  // 프로필 이미지
  @GetMapping("/")
  public ResponseEntity<List<ProfileImage>> getAllProfileImage() {
    List<ProfileImage> listProfileImage = profileImgService.getAllProfileImg();
    return ResponseEntity.ok(listProfileImage);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProfileImage> updateProfileImage(@PathVariable Long id, @RequestBody ProfileImgDTO profileImgDTO) {
    ProfileImage updateProfileImage = profileImgService.updateProfileImage(id, profileImgDTO);
    return ResponseEntity.ok(updateProfileImage);
  }

  @DeleteMapping("/{id}/complete")
  public ResponseEntity<Void> deleteProfileImage(@PathVariable Long id) {
    profileImgService.deleteProfileImage(id);
    return ResponseEntity.noContent().build();
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
}
