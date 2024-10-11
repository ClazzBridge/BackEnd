package com.example.academy.controller;


import com.example.academy.domain.mysql.Classroom;
import com.example.academy.dto.classroom.AddClassroomDTO;
import com.example.academy.dto.classroom.ClassroomNameDTO;
import com.example.academy.dto.classroom.UpdateClassroomDTO;
import com.example.academy.service.ClassroomService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {

  @Autowired
  private ClassroomService classroomService;

  @GetMapping("/name")
  @Operation(summary = "강의실명 전체 조회")
  public List<ClassroomNameDTO> getClassroomName(){
    List<ClassroomNameDTO> classroomNameDTOS = classroomService.getClassroomName();
    return classroomNameDTOS;
  }

  @PostMapping
  @Operation(summary = "강의실 추가")
  public ResponseEntity<String> addClassroom(@RequestBody AddClassroomDTO addClassroomDTO){
    classroomService.addClassroom(addClassroomDTO);
    return ResponseEntity.ok("추가완료");
  }

  @GetMapping
  @Operation(summary = "강의실 전체 조회")
  public ResponseEntity<List<?>> getAllClassroom(){
    List<Classroom> name = classroomService.getAllClassroom();
    return ResponseEntity.ok(name);
  }

  @GetMapping("{id}")
  @Operation(summary = "강의실 조회")
  public ResponseEntity<Optional<?>> getClassroom(@PathVariable Long id){
    Optional<Classroom> name = classroomService.getClassroom(id);
    if (name.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.status(HttpStatus.OK).body(name);
  }

  @DeleteMapping
  @Operation(summary = "강의실 삭제")
  public ResponseEntity<?> deleteClassroom(Long id){
    classroomService.deleteClassroom(id);
    return ResponseEntity.ok("삭제 성공");
  }

  @PutMapping
  @Operation(summary = "강의실 변경")
  public ResponseEntity<?> updateClassroom(@RequestBody UpdateClassroomDTO updateClassroomDTO){
    classroomService
    return ResponseEntity.ok(updateClassroomDTO);
  }

}
