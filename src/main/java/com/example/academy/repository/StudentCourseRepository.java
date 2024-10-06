package com.example.academy.repository;

import com.example.academy.domain.Member;
import com.example.academy.domain.StudentCourse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRepository extends JpaRepository<StudentCourse,Long> {

  List<StudentCourse> findByStudent(Member student);
}
