package com.example.academy.service;

import com.example.academy.domain.mysql.Classroom;
import com.example.academy.dto.ClassroomNameDTO;
import com.example.academy.repository.mysql.ClassroomRepository;
import com.example.academy.repository.mysql.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {


  private final ClassroomRepository classroomRepository;


  public ClassroomService(ClassroomRepository classroomRepository) {
    this.classroomRepository = classroomRepository;
  }

  public List<ClassroomNameDTO> getClassroomName(){
    return classroomRepository.findAll().stream()
        .map(classroom -> new ClassroomNameDTO(classroom.getName())) // ClassroomNameDTO로 변환
        .collect(Collectors.toList()); // List로 수집
  }
}
