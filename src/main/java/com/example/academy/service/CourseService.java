package com.example.academy.service;

import com.example.academy.dto.course.CourseNameDTO;
import com.example.academy.repository.mysql.CourseRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public List<CourseNameDTO> getCourseName(){
    return courseRepository.findAll().stream()
        .map(course -> new CourseNameDTO(course.getTitle())) // ClassroomNameDTO로 변환
        .collect(Collectors.toList()); // List로 수집
  }
}
