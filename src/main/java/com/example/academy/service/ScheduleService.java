package com.example.academy.service;

import com.example.academy.domain.Schedule;
import com.example.academy.dto.ScheduleAddDTO;
import com.example.academy.repository.ScheduleRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;

  public ScheduleService(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }

  public List<Schedule> getScheduleAll() {
    return scheduleRepository.findAll();
  }

  public Optional<Schedule> getScheduleById(Long id) {
    return scheduleRepository.findById(id);
  }

  public void addSchedule(ScheduleAddDTO schedule) {

    Long classroomId = schedule.getClassroomId(); // 강의실 ID

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

}
