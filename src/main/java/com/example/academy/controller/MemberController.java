package com.example.academy.controller;

import com.example.academy.dto.LoginRequestDTO;
import com.example.academy.dto.GetMemberDTO;
import com.example.academy.service.JoinService;
import com.example.academy.service.MemberListService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class MemberController {

  private final MemberListService memberListService;
  private final JoinService joinService;

  public MemberController(MemberListService memberListService, JoinService joinService) {
    this.memberListService = memberListService;
    this.joinService = joinService;
  }

  @PostMapping
  @Operation(summary = "회원 등록")
  public ResponseEntity<String> joinProcess(@RequestBody LoginRequestDTO joinDTO) {
    try {
      joinService.joinProcess(joinDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body("회원 등록 완료");
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
  @Operation(summary = "회원 조회")
  @GetMapping("/{id}")
  public ResponseEntity<GetMemberDTO> getMemberWithCourseInfo(@PathVariable Long id) {
    GetMemberDTO memberDTO = memberListService.getMemberWithCourseInfo(id);
    return ResponseEntity.ok(memberDTO);
  }

  @Operation(summary = "전체 회원 조회")
  @GetMapping
  public ResponseEntity<List<GetMemberDTO>> getAllMembersWithCoursesInfo() {
    List<GetMemberDTO> memberDTOs = memberListService.getAllMembersWithCourses();
    return ResponseEntity.ok(memberDTOs);
  }

  @Operation(summary = "회원 삭제")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteMembers(@PathVariable Long id) {
    memberListService.deleteMember(id);
    return ResponseEntity.ok().body("삭제 완료");
  }
}


