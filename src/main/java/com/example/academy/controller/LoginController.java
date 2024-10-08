package com.example.academy.controller;

import com.example.academy.dto.LoginRequestDTO;
import com.example.academy.dto.LoginResponseDTO;
import com.example.academy.service.MemberListService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

  private final MemberListService memberListService;

  public LoginController(MemberListService memberListService) {
    this.memberListService = memberListService;
  }

  @PostMapping
  @Operation(summary = "사용자 로그인")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO req) {

    return memberListService.login(req.getMemberId(), req.getPassword());
  }
}
