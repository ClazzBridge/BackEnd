package com.example.academy.service;

import com.example.academy.domain.Schedule;
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

}
