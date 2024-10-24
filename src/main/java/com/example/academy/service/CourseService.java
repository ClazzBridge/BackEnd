package com.example.academy.service;

import com.example.academy.domain.mysql.Course;
import com.example.academy.dto.course.CourseTitleDTO;
import com.example.academy.dto.course.GetCourseDTO;
import com.example.academy.dto.course.SelectCourseDTO;
import com.example.academy.dto.member.CustomUserDetails;
import com.example.academy.enums.MemberRole;
import com.example.academy.exception.common.NotFoundException;
import com.example.academy.repository.mysql.CourseRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

  private final CourseRepository courseRepository;
  private final AuthService authService;

  public CourseService(CourseRepository courseRepository,  AuthService authService) {
    this.courseRepository = courseRepository;
    this.authService = authService;
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

  public List<SelectCourseDTO> seatAllCourse() {
    List<Course> courses = courseRepository.findAll();

    return courses.stream()
        .map(course -> new SelectCourseDTO(
            course.getId(),
            course.getTitle()))
        .collect(Collectors.toList());
  }


  public Long getTeacherByCourseId() {
    CustomUserDetails user = authService.getAuthenticatedUser();

    if (user.getUserType().equals(MemberRole.ROLE_TEACHER)) {
      Course course = courseRepository.findByInstructorId(user.getUserId())
          .orElseThrow(() -> new NotFoundException("해당 강의가 존재하지 않습니다."));

      return course.getId();
    }
      return null;
  }
}
