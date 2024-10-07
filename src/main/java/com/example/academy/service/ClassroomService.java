package com.example.academy.service;

import com.example.academy.domain.Classroom;
import com.example.academy.repository.ClassroomRepository;
import com.example.academy.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {


  private final ClassroomRepository classroomRepository;


  public ClassroomService(ClassroomRepository classroomRepository) {
    this.classroomRepository = classroomRepository;
  }

  public List<String> getClassroomName(){
    List<Classroom> classrooms =  classroomRepository.findAll();
    System.out.println(classrooms.get(1).getName());
    List<String> name = new ArrayList<>();
    for (Classroom classroom : classrooms) {
      name.add(classroom.getName());
    }

    return name;
  }
}
