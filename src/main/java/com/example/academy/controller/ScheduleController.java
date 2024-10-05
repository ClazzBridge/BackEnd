package com.example.academy.controller;

import com.example.academy.dto.ScheduleAddDTO;
import com.example.academy.dto.ScheduleListDTO;
import com.example.academy.service.ScheduleService;
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
@RequestMapping("/api/schedule")
public class ScheduleController {

  @Autowired
  private ScheduleService scheduleService;

  @GetMapping("/find")
  public ResponseEntity<List<ScheduleListDTO>> getAllSchedules() {
    List<ScheduleListDTO> scheduleList = scheduleService.getScheduleAll();

    if (scheduleList.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT)
          .body(scheduleList); // 데이터가 없으면 204 No Content
    }

    return ResponseEntity.status(HttpStatus.OK).body(scheduleList); // 데이터가 있으면 200 OK
  }

  @GetMapping("/{id}")
  public ResponseEntity<ScheduleListDTO> getScheduleById(@PathVariable("id") Long id) {
    Optional<ScheduleListDTO> scheduleAddDTOOptional = scheduleService.getScheduleById(id);

    if (scheduleAddDTOOptional.isPresent()) {
      // 값이 있을 때는 200 OK와 함께 ScheduleAddDTO 반환
      return ResponseEntity.status(HttpStatus.OK).body(scheduleAddDTOOptional.get());
    } else {
      // 값이 없을 때는 404 Not Found 반환
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }


  @PostMapping("/add")
  public ResponseEntity<?> addSchedule(@RequestBody ScheduleAddDTO addDTO) {
    try {
      scheduleService.addSchedule(addDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(addDTO);
    } catch (IllegalArgumentException e) {
      // Classroom을 찾지 못한 경우
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      // 그 외의 예외 처리
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("An error occurred: " + e.getMessage());
    }
  }
  
  @PutMapping("/update")
  public ResponseEntity<ScheduleListDTO> updateSchedule(@RequestBody ScheduleListDTO updateDTO) {
    System.out.println("업데이트");
    scheduleService.updateSchedule(updateDTO);
    return ResponseEntity.status(HttpStatus.OK).body(updateDTO);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteSchedule(@PathVariable Long id) {
    try {
      scheduleService.deleteSchedule(id);
      return ResponseEntity.ok("ok"); // 성공 시 200 OK와 함께 "ok" 반환
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: 일정 삭제 실패"); // 실패 시 404 응답
    }
  }
}