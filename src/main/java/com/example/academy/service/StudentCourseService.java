package com.example.academy.service;

import com.example.academy.dto.member.CustomUserDetails;
import com.example.academy.repository.mysql.StudentCourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;

    private final AuthService authService;

    public StudentCourseService(StudentCourseRepository studentCourseRepository,
        AuthService authService) {
        this.studentCourseRepository = studentCourseRepository;
        this.authService = authService;
    }


    public Long getCoureseId() {
        CustomUserDetails user = authService.getAuthenticatedUser();

        Long courseId = studentCourseRepository.findByStudentId(user.getUserId()).getCourse()
            .getId();

        System.out.println(courseId + "====================================================");
        return courseId;
    }
}
