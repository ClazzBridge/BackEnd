package com.example.academy.service;

import com.example.academy.domain.Course;
import com.example.academy.dto.course.CourseAddDTO;
import com.example.academy.dto.course.CourseTitleDTO;
import com.example.academy.dto.course.CourseUpdateDTO;
import com.example.academy.dto.course.GetCourseDTO;
import com.example.academy.repository.mysql.ClassroomRepository;
import com.example.academy.repository.mysql.CourseRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.text.html.Option;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

  private final CourseRepository courseRepository;
  private final ClassroomRepository classroomRepository;

  public CourseService(CourseRepository courseRepository, ClassroomRepository classroomRepository) {
    this.courseRepository = courseRepository;
    this.classroomRepository = classroomRepository;
  }

  public List<CourseTitleDTO> getCourseTitle() {
    return courseRepository.findAll().stream()
        .map(course -> new CourseTitleDTO(course.getTitle())) // ClassroomNameDTO로 변환
        .collect(Collectors.toList()); // List로 수집
  }

  public List<GetCourseDTO> getAllCourse() {
    List<Course> courses = courseRepository.findAll();  // Lazy로 인해 N+1 문제가 발생할 수 있음. 이를 해결하려면 JOIN FETCH 사용 고려.

    return courses.stream()
        .map(course -> new GetCourseDTO(
            course.getId(),
            course.getInstructor() != null ? course.getInstructor().getName() : "No Instructor",
            // null 체크
            course.getClassroom() != null ? course.getClassroom().getName() : "No Classroom",
            // null 체크
            course.getTitle(),
            course.getDescription(),
            course.getStartDate(),
            course.getEndDate(),
            course.getLayoutImageUrl()))
        .collect(Collectors.toList());
  }

  public List<GetCourseDTO> getCourse(Long id) throws Exception {
    Optional<Course> courses = courseRepository.findById(
        id);  // Lazy로 인해 N+1 문제가 발생할 수 있음. 이를 해결하려면 JOIN FETCH 사용 고려.

    if (courses.isEmpty()) {
      throw new Exception("조회된 강의가 없습니다.");
    }
    return courses.stream() // 1. courses 리스트에서 Stream(Data 순차 처리)을 생성
        .map(course -> new GetCourseDTO( // 2. 각 course 객체를 GetCourseDTO로 변환하는 작업
            course.getId(), // 3. course의 ID를 GetCourseDTO에 설정
            course.getInstructor() != null ? course.getInstructor().getName() : "No Instructor",
            // 4. 강사 정보가 있으면 이름을, 없으면 "No Instructor"를 설정
            course.getClassroom() != null ? course.getClassroom().getName() : "No Classroom",
            // 5. 교실 정보가 있으면 이름을, 없으면 "No Classroom"을 설정
            course.getTitle(), // 6. 강의 제목을 GetCourseDTO에 설정
            course.getDescription(), // 7. 강의 설명을 GetCourseDTO에 설정
            course.getStartDate(), // 8. 시작 날짜를 GetCourseDTO에 설정
            course.getEndDate(), // 9. 종료 날짜를 GetCourseDTO에 설정
            course.getLayoutImageUrl())) // 10. 레이아웃 이미지 URL을 GetCourseDTO에 설정
        .collect(Collectors.toList()); // 11. 변환된 결과들을 리스트로 수집하여 반환
  }


  public void deleteCourse(Long id) {
    courseRepository.deleteById(id);
  }


  public void addCourse(CourseAddDTO courseAddDTO) throws Exception {

    String message = "";
    while (true) {
      if (courseRepository.existsByTitle(courseAddDTO.getTitle())) {
        message += "이미 존재하는 강의명입니다.";
      }
      if (classroomRepository.existsByName(courseAddDTO.getClassroomName())) {
        if (classroomRepository.findByName(courseAddDTO.getClassroomName()).get().getIsOccupied()) {
          message += " 해당 강의실은 사용 중입니다.";
        }
      }else {
        message += " 해당 강의실은 존재하지 않습니다.";
      }
      if (message.equals("")) {
        break;
      } else {
        throw new Exception(message);
      }
    }
      Course course = new Course();
      course.setClassroom(classroomRepository.findByName(courseAddDTO.getClassroomName()).get());
      course.setTitle(courseAddDTO.getTitle());
      course.setDescription(courseAddDTO.getDescription());
      course.setStartDate(courseAddDTO.getStartDate());
      course.setEndDate(courseAddDTO.getEndDate());
      course.setLayoutImageUrl(courseAddDTO.getLayoutImageUrl());
      courseRepository.save(course);
  }

  public void updateCourse(CourseUpdateDTO courseUpdateDTO) throws Exception{

    Course course = courseRepository.findById(courseUpdateDTO.getId()).get();

    String message = "";
    while (true) {
      if (!course.getTitle().equals(courseUpdateDTO.getTitle()) && courseRepository.existsByTitle(courseUpdateDTO.getTitle())) {
        message += "이미 존재하는 강의명입니다.";
      }
      if (classroomRepository.existsByName(courseUpdateDTO.getClassroomName())) {
        if (!course.getClassroom().getName().equals(courseUpdateDTO.getClassroomName())
            && classroomRepository.findByName(courseUpdateDTO.getClassroomName()).get().getIsOccupied()) {
          message += " 해당 강의실은 사용 중입니다.";
        }
      }else {
        message += " 해당 강의실은 존재하지 않습니다.";
      }
      if (message.equals("")) {
        break;
      } else {
        throw new Exception(message);
      }
    }

    course.setClassroom(classroomRepository.findByName(courseUpdateDTO.getClassroomName()).get());
    course.setTitle(courseUpdateDTO.getTitle());
    course.setDescription(courseUpdateDTO.getDescription());
    course.setStartDate(courseUpdateDTO.getStartDate());
    course.setEndDate(courseUpdateDTO.getEndDate());
    course.setLayoutImageUrl(courseUpdateDTO.getLayoutImageUrl());

    courseRepository.save(course);
  }
}
