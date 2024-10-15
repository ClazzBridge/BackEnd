package com.example.academy.repository.mysql;

import com.example.academy.domain.mysql.Member;
import com.example.academy.domain.mysql.StudentCourse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    List<StudentCourse> findByStudent(Member student);

    Optional<StudentCourse> findById(Long id);

    Optional<StudentCourse> findByStudentIdAndCourseId(Long id, Long id1);
}
