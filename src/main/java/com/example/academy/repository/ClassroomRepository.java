package com.example.academy.repository;

import com.example.academy.domain.Classroom;
import com.example.academy.domain.Member;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

  Optional<Classroom> findByName(String name); // 강의실 이름으로 조회


  List<Classroom> findAll();
}
