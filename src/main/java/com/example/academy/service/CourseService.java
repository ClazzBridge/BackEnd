package com.example.academy.service;

import com.example.academy.domain.Course;
import com.example.academy.dto.course.CourseTitleDTO;
import com.example.academy.dto.course.GetCourseDTO;
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

  public List<CourseTitleDTO> getCourseTitle(){
    return courseRepository.findAll().stream()
        .map(course -> new CourseTitleDTO(course.getTitle())) // ClassroomNameDTO로 변환
        .collect(Collectors.toList()); // List로 수집
  }

  public List<GetCourseDTO> getAllCourse() {
    List<Course> courses = courseRepository.findAll();  // Lazy로 인해 N+1 문제가 발생할 수 있음. 이를 해결하려면 JOIN FETCH 사용 고려.

    return courses.stream()
        .map(course -> new GetCourseDTO(
            course.getId(),
            course.getInstructor() != null ? course.getInstructor().getName() : "No Instructor", // null 체크
            course.getClassroom() != null ? course.getClassroom().getName() : "No Classroom",    // null 체크
            course.getTitle(),
            course.getDescription(),
            course.getStartDate(),
            course.getEndDate(),
            course.getLayoutImageUrl()))
        .collect(Collectors.toList());
  }

  public void deleteCourse(Long id){
    courseRepository.deleteById(id);
  }


}
