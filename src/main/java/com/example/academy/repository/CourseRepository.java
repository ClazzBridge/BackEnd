package com.example.academy.repository;

import com.example.academy.domain.Course;
import com.example.academy.domain.Member;
import com.example.academy.domain.StudentCourse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
  List<Course> findByInstructor(Member instructor);

  Optional<Course> findByTitle(String title);

  boolean existsByTitle(String title);
 }
