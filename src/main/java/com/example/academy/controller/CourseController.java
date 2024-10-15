package com.example.academy.controller;


import com.example.academy.dto.course.CourseTitleDTO;
import com.example.academy.dto.course.GetCourseDTO;
import com.example.academy.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
public class CourseController {

  @Autowired
  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @Operation(summary = "강의명 전체 조회", security = {@SecurityRequirement(name = "bearerAuth")})
  @GetMapping("/title")
  public ResponseEntity<List<?>> getCourseTitle(){
    List<CourseTitleDTO> title = courseService.getCourseTitle();
    return ResponseEntity.ok(title);
  }
  @Operation(summary = "강의 전체 조회", security = {@SecurityRequirement(name = "bearerAuth")})
  @GetMapping
  public ResponseEntity<List<GetCourseDTO>> getAllCourse(){
    return ResponseEntity.ok(courseService.getAllCourse());
  }

}
