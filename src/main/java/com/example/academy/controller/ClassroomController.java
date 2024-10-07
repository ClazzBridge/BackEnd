package com.example.academy.controller;


import com.example.academy.dto.ClassroomNameDTO;
import com.example.academy.service.ClassroomService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {

  @Autowired
  private ClassroomService classroomService;

  @GetMapping
  @Operation(summary = "강의실명 전체 조회")
  public ResponseEntity<List<?>> getClassroomName(){
    List<ClassroomNameDTO> name = classroomService.getClassroomName();

    return ResponseEntity.ok(name);
  }
}
