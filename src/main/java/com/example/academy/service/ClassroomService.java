package com.example.academy.service;

import static java.lang.Boolean.FALSE;

import com.example.academy.domain.mysql.Classroom;
import com.example.academy.dto.classroom.AddClassroomDTO;
import com.example.academy.dto.classroom.ClassroomNameDTO;
import com.example.academy.dto.classroom.GetClassroomDTO;
import com.example.academy.dto.classroom.UpdateClassroomDTO;
import com.example.academy.repository.mysql.ClassroomRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {


  private final ClassroomRepository classroomRepository;


  public ClassroomService(ClassroomRepository classroomRepository) {
    this.classroomRepository = classroomRepository;
  }

  public void addClassroom(AddClassroomDTO addClassroomDTO) {
    String name =  addClassroomDTO.getName();

    Classroom classroom = new Classroom();
    classroom.setName(name);
    classroom.setIsOccupied(false);

    classroomRepository.save(classroom);
  }

  public Classroom updateClassroom(UpdateClassroomDTO updateClassroomDTO) {

    Long id = updateClassroomDTO.getId();
    String name = updateClassroomDTO.getName();
    Boolean isOccupied = updateClassroomDTO.getIsOccupied();


    Optional<Classroom> classroom = classroomRepository.findById(id);
    if (classroom.isEmpty()) {
      throw new NoSuchElementException("해당 강의실이 없습니다.");
    }
    Classroom newClassroom = classroom.get();

    newClassroom.setId(id);
    newClassroom.setName(name);
    newClassroom.setIsOccupied(isOccupied);

    return classroomRepository.save(newClassroom);
  }

  public List<ClassroomNameDTO> getClassroomName(){
    List<ClassroomNameDTO> classroomNameDTOS =  classroomRepository.findAll().stream()
        .map(classroom -> new ClassroomNameDTO(classroom.getName())) // ClassroomNameDTO로 변환
        .collect(Collectors.toList()); // List로 수집
    return classroomNameDTOS;
  }

  public List<Classroom> getAllClassroom(){
    return classroomRepository.findAll();
  }

  public Optional<Classroom> getClassroom(Long id)  {
    Optional<Classroom> classroom =classroomRepository.findById(id);
    if (!classroom.isPresent()) {
      return Optional.empty();
    }
    return classroomRepository.findById(id);
  }


  public void deleteClassroom(Long id){
    classroomRepository.deleteById(id);
  }
}
