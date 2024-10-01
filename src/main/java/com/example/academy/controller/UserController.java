package com.example.academy.controller;

import com.example.academy.domain.ProfileImage;
import com.example.academy.dto.LoginRequestDTO;
import com.example.academy.dto.LoginResponseDTO;
import com.example.academy.service.JoinService;
import com.example.academy.service.UserListService;
import com.example.academy.type.MemberType;
import java.util.Date;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
  public Optional<LoginResponseDTO> login(@RequestBody LoginRequestDTO req) {

    return userListService.login(req.getMemberId(), req.getPassword());
  }

  @PostMapping("/join")
  public String joinProcess(@RequestBody LoginRequestDTO joinDTO) {
    try {
      joinService.joinProcess(joinDTO);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "ok";
  }
}


