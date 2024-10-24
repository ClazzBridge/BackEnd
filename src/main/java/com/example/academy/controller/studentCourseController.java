package com.example.academy.controller;

import com.example.academy.service.StudentCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/studentCourses")
public class studentCourseController {

    private final StudentCourseService studentCourseService;

    public studentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }


    @GetMapping("")
    public ResponseEntity<Long> getCourseId() {
        Long courseId = studentCourseService.getCoureseId();

        return ResponseEntity.ok().body(courseId);
    }






}
