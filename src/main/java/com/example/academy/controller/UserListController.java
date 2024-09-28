package com.example.academy.controller;

import com.example.academy.domain.UserList;
import com.example.academy.service.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserListController {

  private UserListService userListService;


  @Autowired
  public UserListController(UserListService userListService) {
    this.userListService = userListService;
  }

  // 프로필 이미지 조회 / 변경 / 삭제 ㅇ
  // 좌석 등록 / 조회 / 변경
  // 프로필 모달창 유저 정보 출력 ㅇ

// 유저 정보 출력
  @GetMapping("/{id}")
  public ResponseEntity<UserList> getUserById(@PathVariable Long id) {
    UserList userList = userListService.getUserById(id);
    return ResponseEntity.ok(userList);
  }
}
