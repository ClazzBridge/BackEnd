package com.example.academy.service;

import com.example.academy.domain.Classroom;
import com.example.academy.domain.Schedule;
import com.example.academy.dto.ScheduleAddDTO;
import com.example.academy.dto.ScheduleListDTO;
import com.example.academy.repository.ClassroomRepository;
import com.example.academy.repository.ScheduleRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;
  private final ClassroomRepository classroomRepository;

  public ScheduleService(ScheduleRepository scheduleRepository,
      ClassroomRepository classroomRepository) {
    this.scheduleRepository = scheduleRepository;
    this.classroomRepository = classroomRepository;
  }

  public List<ScheduleListDTO> getScheduleAll() {
    List<Schedule> schedules = scheduleRepository.findAll();

    // Schedule 리스트를 ScheduleAddDTO 리스트로 변환
    return schedules.stream().map(schedule -> {
      // 강의실 정보 가져오기
      Long classroomId = schedule.getClassroomId();
      Optional<Classroom> classroomOptional = classroomRepository.findById(classroomId);

      String classroomName =
          classroomOptional.isPresent() ? classroomOptional.get().getName() : "Unknown";

      // ScheduleAddDTO로 변환
      ScheduleListDTO scheduleListDTO = new ScheduleListDTO();
      scheduleListDTO.setId(schedule.getId());
      scheduleListDTO.setClassroomName(classroomName);
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

    Long classroomId = schedule.getClassroomId();
    Optional<Classroom> classroomOptional = classroomRepository.findById(classroomId);

    if (!classroomOptional.isPresent()) {
      return Optional.empty();
    }

    Classroom classroom = classroomOptional.get();
    String classroomName = classroom.getName(); // 강의실 명 추출

    ScheduleListDTO scheduleListDTO = new ScheduleListDTO();
    scheduleListDTO.setId(id);
    scheduleListDTO.setClassroomName(classroomName);
    scheduleListDTO.setEventTitle(schedule.getEventTitle());
    scheduleListDTO.setStartDate(schedule.getStartDate());
    scheduleListDTO.setEndDate(schedule.getEndDate());
    scheduleListDTO.setDescription(schedule.getDescription());

    return Optional.of(scheduleListDTO);
  }


  public void addSchedule(ScheduleAddDTO schedule) {

    String classroomName = schedule.getClassroomName();

    // classroomId를 찾기
    Optional<Classroom> optionalClassroom = classroomRepository.findByName(classroomName);
    if (optionalClassroom.isEmpty()) {
      // classroomId를 찾지 못하면 null을 반환하거나 적절한 값을 처리하도록 설정
      throw new IllegalArgumentException("Classroom not found: " + classroomName);
    }
    
    Long classroomId = classroomRepository.findByName(classroomName).orElseThrow().getId();
    String eventTitle = schedule.getEventTitle(); // 일정 제목
    LocalDateTime startDate = schedule.getStartDate(); // 일정 시작 날짜
    LocalDateTime endDate = schedule.getEndDate(); // 일정 종료 날짜
    String description = schedule.getDescription(); // 일정 설명

    Schedule data = new Schedule();

    data.setClassroomId(classroomId);
    data.setEventTitle(eventTitle);
    data.setStartDate(startDate);
    data.setEndDate(endDate);
    data.setDescription(description);

    scheduleRepository.save(data);
  }

  public void updateSchedule(ScheduleListDTO schedule) {
    String classroomName = schedule.getClassroomName();

    // classroomRepository에서 classroomName으로 강의실 조회
    Optional<Classroom> classroomOptional = classroomRepository.findByName(classroomName);
    if (!classroomOptional.isPresent()) {
      throw new RuntimeException("해당 이름의 강의실을 찾을 수 없습니다.");
    }

    Long classroomId = classroomOptional.get().getId(); // 강의실 ID 추출
    String eventTitle = schedule.getEventTitle(); // 일정 제목
    LocalDateTime startDate = schedule.getStartDate(); // 일정 시작 날짜
    LocalDateTime endDate = schedule.getEndDate(); // 일정 종료 날짜
    String description = schedule.getDescription(); // 일정 설명

    Long scheduleId = schedule.getId(); // 일정 번호

    // scheduleRepository에서 일정 조회
    Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
    if (!scheduleOptional.isPresent()) {
      throw new RuntimeException("해당 ID의 일정을 찾을 수 없습니다.");
    }

    Schedule data = scheduleOptional.get(); // Optional에서 Schedule 추출

    // Schedule 데이터를 업데이트
    data.setClassroomId(classroomId);
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
