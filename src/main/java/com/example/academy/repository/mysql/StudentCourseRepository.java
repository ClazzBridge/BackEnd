package com.example.academy.repository.mysql;

import com.example.academy.domain.mysql.Course;
import com.example.academy.domain.mysql.Member;
import com.example.academy.domain.mysql.StudentCourse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

  Optional<StudentCourse> findById(Long id);

  List<StudentCourse> findByStudent(Member student);

  List<StudentCourse> findByCourse(Course course);

  StudentCourse findByStudentAndCourse(Member student, Course course);

}
