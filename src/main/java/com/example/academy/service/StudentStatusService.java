package com.example.academy.service;

import com.example.academy.domain.StudentStatus;
import com.example.academy.dto.StudentStatusReadDTO;
import com.example.academy.dto.StudentStatusUpdateHandRaisedDTO;
import com.example.academy.dto.StudentStatusUpdateUnderstandingDTO;
import com.example.academy.mapper.StudentStatusMapper;
import com.example.academy.repository.StudentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentStatusService {

  private final StudentStatusRepository studentStatusRepository;
  private final StudentStatusMapper studentStatusMapper = StudentStatusMapper.INSTANCE;

  @Autowired
  public StudentStatusService(StudentStatusRepository studentStatusRepository) {
    this.studentStatusRepository = studentStatusRepository;
  }

  public StudentStatusReadDTO updateIsUnderstand(
      StudentStatusUpdateUnderstandingDTO studentStatusUpdateUnderstandingDTO) {
    StudentStatus existingStudentStatus = studentStatusRepository.findByMemberId(
        studentStatusUpdateUnderstandingDTO.getMemberId());

    existingStudentStatus.setUnderstanding(studentStatusUpdateUnderstandingDTO.isUnderstanding());
    StudentStatus updatedStudentStatus = studentStatusRepository.save(existingStudentStatus);

    return studentStatusMapper.studentStatusToStudentStatusReadDTO(updatedStudentStatus);
  }

  public StudentStatusReadDTO updateIsRaiseHand(
      StudentStatusUpdateHandRaisedDTO studentStatusUpdateHandRaisedDTO) {
    StudentStatus existingStudentStatus = studentStatusRepository.findByMemberId(
        studentStatusUpdateHandRaisedDTO.getMemberId());

    existingStudentStatus.setHandRaised(studentStatusUpdateHandRaisedDTO.isHandRaised());
    StudentStatus updatedStudentStatus = studentStatusRepository.save(existingStudentStatus);

    return studentStatusMapper.studentStatusToStudentStatusReadDTO(updatedStudentStatus);
  }

}
