package com.example.academy.repository.mysql;

import com.example.academy.domain.mysql.Course;
import com.example.academy.domain.mysql.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

  List<Course> findByInstructor(Member instructor);

  Optional<Course> findByTitle(String title);

  boolean existsByTitle(String title);
}
