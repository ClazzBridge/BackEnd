package com.example.academy.controller;

import com.example.academy.dto.LoginRequestDTO;
import com.example.academy.dto.LoginResponseDTO;
import com.example.academy.service.JoinService;
import com.example.academy.service.UserListService;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserListService userListService;
  private final JoinService joinService;

  public UserController(UserListService userListService, JoinService joinService) {
    this.userListService = userListService;
    this.joinService = joinService;
  }

  @PostMapping("/sign")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO req) {

    return userListService.login(req.getMemberId(), req.getPassword());
  }

  @PostMapping("/join")
  public ResponseEntity<String> joinProcess(@RequestBody LoginRequestDTO joinDTO) {
    try {
      joinService.joinProcess(joinDTO);
      return ResponseEntity.ok("가입 성공");
    } catch (DataIntegrityViolationException e) { // 중복 값 등으로 인한 DB 제약 조건 위반
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 값이 있습니다.");
    } catch (IllegalArgumentException e) { // 서비스에서 던진 중복 예외 처리
      return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    } catch (Exception e) { // 그 외의 일반적인 예외 처리
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 발생");
    }
  }
}


