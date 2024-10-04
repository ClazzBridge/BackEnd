package com.example.academy.controller;

import com.example.academy.domain.StudentStatus;
import com.example.academy.dto.StudentStatusReadDTO;
import com.example.academy.dto.StudentStatusUpdateHandRaisedDTO;
import com.example.academy.dto.StudentStatusUpdateUnderstandingDTO;
import com.example.academy.repository.StudentStatusRepository;
import com.example.academy.service.StudentStatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student-status")
public class StudentStatusController {

  private final StudentStatusService studentStatusService;
  private final StudentStatusRepository studentStatusRepository;

  @Autowired
  public StudentStatusController(StudentStatusService studentStatusService,
      StudentStatusRepository studentStatusRepository) {
    this.studentStatusService = studentStatusService;
    this.studentStatusRepository = studentStatusRepository;
  }

  /**
   * 시트 컨트롤러 필요 기능들
   * <p>
   * 이해 완료 버튼 Click -> Set 이해 미완료
   * <p>
   * 이해 미완료 버튼 CLick -> Set 이해 완료
   * <p>
   * 손 들기 버튼 CLick -> Set RaiseHand On
   * <p>
   * 손 든 후 시간 지나면 -> Set RaiseHand Off
   */

  @PutMapping("/understanding")
  public ResponseEntity<StudentStatusReadDTO> updateIsUnderstanding(@RequestBody
  StudentStatusUpdateUnderstandingDTO studentStatusUpdateUnderstandingDTO) {
    StudentStatusReadDTO updatedStudentStatusReadDTO = studentStatusService.updateIsUnderstand(
        studentStatusUpdateUnderstandingDTO);

    return ResponseEntity.ok(updatedStudentStatusReadDTO);
  }

  @PutMapping("/hand-raised")
  public ResponseEntity<StudentStatusReadDTO> updateIsHandRaised(@RequestBody
  StudentStatusUpdateHandRaisedDTO seatToggleRaiseHandDTO) {
    StudentStatusReadDTO updatedStudentStatusReadDTO = studentStatusService.updateIsRaiseHand(
        seatToggleRaiseHandDTO);

    return ResponseEntity.ok(updatedStudentStatusReadDTO);
  }
}
