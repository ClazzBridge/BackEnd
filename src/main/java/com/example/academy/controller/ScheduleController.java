package com.example.academy.controller;

import com.example.academy.domain.Member;
import com.example.academy.domain.Schedule;
import com.example.academy.service.ScheduleService;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

  @Autowired
  private ScheduleService scheduleService;

  @GetMapping("/find")
  public ResponseEntity<List<Schedule>> getScheduleByAll() {
    List<Schedule> schedule = scheduleService.getScheduleAll();

    return ResponseEntity.ok(schedule);
  }

  @PostMapping("/{id}")
  public ResponseEntity<Optional<Schedule>> getScheduleById(@PathVariable("id") Long id) {

    return ResponseEntity.ok(scheduleService.getScheduleById(id));
  }
}
