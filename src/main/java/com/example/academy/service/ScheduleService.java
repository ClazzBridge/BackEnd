package com.example.academy.service;

import com.example.academy.domain.mysql.Course;
import com.example.academy.domain.mysql.Schedule;
import com.example.academy.dto.schedule.ScheduleAddDTO;
import com.example.academy.dto.schedule.ScheduleListDTO;
import com.example.academy.repository.mysql.CourseRepository;
import com.example.academy.repository.mysql.ScheduleRepository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;
  private final CourseRepository courseRepository;


  public ScheduleService(ScheduleRepository scheduleRepository,
      CourseRepository courseRepository) {
    this.scheduleRepository = scheduleRepository;
    this.courseRepository = courseRepository;
  }

  public List<ScheduleListDTO> getScheduleAll() {
    List<Schedule> schedules = scheduleRepository.findAll();

    // Schedule 리스트를 ScheduleAddDTO 리스트로 변환
    return schedules.stream().map(schedule -> {
      // 강의실 정보 가져오기
      Long courseId = schedule.getCourse().getId();
      Optional<Course> courseOptional = courseRepository.findById(courseId);

      String courseName =
          courseOptional.isPresent() ? courseOptional.get().getTitle() : "Unknown";

      // ScheduleAddDTO로 변환
      ScheduleListDTO scheduleListDTO = new ScheduleListDTO();
      scheduleListDTO.setId(schedule.getId());
      scheduleListDTO.setCourseTitle(courseName);
      scheduleListDTO.setEventTitle(schedule.getEventTitle());
      scheduleListDTO.setStartDate(schedule.getStartDate());
      scheduleListDTO.setEndDate(schedule.getEndDate());
      scheduleListDTO.setDescription(schedule.getDescription());

      return scheduleListDTO;
    }).collect(Collectors.toList());
  }


  public Optional<ScheduleListDTO> getScheduleById(Long id) {
    Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);

    if (!scheduleOptional.isPresent()) {
      return Optional.empty();
    }

    Schedule schedule = scheduleOptional.get();

    Long courseId = schedule.getCourse().getId();
    Optional<Course> courseOptional = courseRepository.findById(courseId);

    if (!courseOptional.isPresent()) {
      return Optional.empty();
    }

    Course course = courseOptional.get();
    String courseTitle = course.getTitle(); // 강의실 명 추출

    ScheduleListDTO scheduleListDTO = new ScheduleListDTO();
    scheduleListDTO.setId(id);
    scheduleListDTO.setCourseTitle(courseTitle);
    scheduleListDTO.setEventTitle(schedule.getEventTitle());
    scheduleListDTO.setStartDate(schedule.getStartDate());
    scheduleListDTO.setEndDate(schedule.getEndDate());
    scheduleListDTO.setDescription(schedule.getDescription());

    return Optional.of(scheduleListDTO);
  }


  public void addSchedule(ScheduleAddDTO schedule) {

    String courseTitle = schedule.getCourseTitle();

    // courseName 찾기
    Optional<Course> optionalCourse = courseRepository.findByTitle(courseTitle);
    if (optionalCourse.isEmpty()) {
      // courseName를 찾지 못하면 null을 반환하거나 적절한 값을 처리하도록 설정
      throw new IllegalArgumentException("CourseName not found: " + courseTitle);
    }

    Course courseId = courseRepository.findByTitle(courseTitle).orElseThrow();
    String eventTitle = schedule.getEventTitle(); // 일정 제목
    Instant startDate = schedule.getStartDate(); // 일정 시작 날짜
    Instant endDate = schedule.getEndDate(); // 일정 종료 날짜
    String description = schedule.getDescription(); // 일정 설명
    if (endDate.isBefore(startDate)) {
      throw new IllegalArgumentException("종료 날짜는 시작 날짜보다 이후여야 합니다.");
    }


    Schedule data = new Schedule();

    data.setCourse(courseId);
    data.setEventTitle(eventTitle);
    data.setStartDate(startDate);
    data.setEndDate(endDate);
    data.setDescription(description);

    scheduleRepository.save(data);
  }

  public void updateSchedule(ScheduleListDTO schedule) {
    String courseTitle = schedule.getCourseTitle();

    // classroomRepository에서 courseName 강의실 조회
    Optional<Course> courseOptional = courseRepository.findByTitle(courseTitle);
    if (!courseOptional.isPresent()) {
      throw new RuntimeException("해당 이름의 강의실을 찾을 수 없습니다.");
    }

    Course course = courseOptional.get(); // 강의 ID 추출
    String eventTitle = schedule.getEventTitle(); // 일정 제목
    Instant startDate = schedule.getStartDate(); // 일정 시작 날짜
    Instant endDate = schedule.getEndDate(); // 일정 종료 날짜
    String description = schedule.getDescription(); // 일정 설명
    if (endDate.isBefore(startDate)) {
      throw new IllegalArgumentException("종료 날짜는 시작 날짜보다 이후여야 합니다.");
    }

    Long scheduleId = schedule.getId(); // 일정 번호

    // scheduleRepository에서 일정 조회
    Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
    if (!scheduleOptional.isPresent()) {
      throw new RuntimeException("해당 ID의 일정을 찾을 수 없습니다.");
    }

    Schedule data = scheduleOptional.get(); // Optional에서 Schedule 추출

    // Schedule 데이터를 업데이트
    data.setCourse(course);
    data.setEventTitle(eventTitle);
    data.setStartDate(startDate);
    data.setEndDate(endDate);
    data.setDescription(description);

    // 변경된 데이터를 저장
    scheduleRepository.save(data);
  }

  public void deleteSchedule(Long id) {
    scheduleRepository.deleteById(id);

  }
}
