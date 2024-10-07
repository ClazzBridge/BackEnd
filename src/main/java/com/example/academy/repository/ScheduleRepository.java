package com.example.academy.repository;

import com.example.academy.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

//  @EntityGraph(attributePaths = {"classroom"})
//    // classroom 관계를 명시적으로 로드
//  List<Schedule> findAll();
}
