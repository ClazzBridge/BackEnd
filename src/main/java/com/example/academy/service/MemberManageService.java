package com.example.academy.service;


import com.example.academy.domain.mysql.Course;
import com.example.academy.domain.mysql.Member;
import com.example.academy.domain.mysql.ProfileImage;
import com.example.academy.domain.mysql.StudentCourse;
import com.example.academy.dto.StudentSignUpDTO;
import com.example.academy.repository.CourseRepository;
import com.example.academy.repository.mysql.MemberRepository;
import com.example.academy.repository.mysql.ProfileImageRepository;
import com.example.academy.repository.StudentCourseRepository;
import com.example.academy.type.MemberType;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberManageService {

  private final MemberRepository memberRepository;
  private final ProfileImageRepository profileImageRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final CourseRepository courseRepository;
  private final StudentCourseRepository studentCourseRepository;


  public MemberManageService(MemberRepository memberRepository,
      ProfileImageRepository profileImageRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
      CourseRepository courseRepository, StudentCourseRepository studentCourseRepository) {
    this.memberRepository = memberRepository;
    this.profileImageRepository = profileImageRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.courseRepository = courseRepository;
    this.studentCourseRepository = studentCourseRepository;
  }

  public ResponseEntity<String> signUp(StudentSignUpDTO studentSignUpDTO) {

    String memberId = studentSignUpDTO.getMemberId();
    String password = studentSignUpDTO.getPassword();
    String name = studentSignUpDTO.getName();
    String email = studentSignUpDTO.getEmail();
    String phone = studentSignUpDTO.getPhone();
    MemberType memberType = studentSignUpDTO.getMemberType();
    String title = studentSignUpDTO.getTitle();

    boolean isExistMember = memberRepository.existsByMemberId(memberId);
    boolean isExistEmail = memberRepository.existsByEmail(email);
    boolean isExistPhone = memberRepository.existsByPhone(phone);
    boolean isExistCourseTitle = courseRepository.existsByTitle(title);

    // 여기서 강의명이 없을 때 예외를 발생시킴
    if (!isExistCourseTitle) {
      throw new DataIntegrityViolationException("강의명 없음.");
    }

    if (isExistMember || isExistEmail || isExistPhone) {
      String errorMessage = "";
      if (isExistMember) {
        errorMessage += "memberId 중복. ";
      }
      if (isExistEmail) {
        errorMessage += "email 중복. ";
      }
      if (isExistPhone) {
        errorMessage += "phone 중복. ";
      }
      System.out.println(errorMessage);
      throw new DataIntegrityViolationException(errorMessage);
    }

    Member data = new Member();

    data.setMemberId(memberId);
    data.setPassword(bCryptPasswordEncoder.encode(password));
    data.setName(name);
    data.setEmail(email);
    data.setPhone(phone);
    data.setMemberType(memberType);
    // ProfileImage 테이블의 총 레코드 수 가져오기
    long count = profileImageRepository.count();

    // 1부터 count까지의 랜덤 ID 생성
    int randomId = (int) (Math.random() * count) + 1;

    // 랜덤 ID에 해당하는 ProfileImage 가져오기
    ProfileImage profileImage = profileImageRepository.findById((long) randomId)
        .orElseThrow(() -> new RuntimeException("ProfileImage not found"));

    // member 객체에 profileImage 설정
    data.setProfileImage(profileImage);

    Member saveMember = memberRepository.save(data);

    Long id = saveMember.getId();

    // 과정명 추가
    // 수강생은 student_course에 데이터 추가
    if(memberType.equals(MemberType.ROLE_STUDENT)){
      Optional<Course> course = courseRepository.findByTitle(title);
      System.out.println();
      StudentCourse studentCourse = new StudentCourse();

      studentCourse.setCourse(course.get());
      studentCourse.setStudent(saveMember);

      studentCourseRepository.save(studentCourse);

      // 강사는 Course에 데이터 업데이트
    }else {
      Optional<Course> course = courseRepository.findByTitle(title);
      course.get().setInstructor(saveMember);
      courseRepository.save(course.get());
    }

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body("회원가입 성공"); // 성공 메시지 반환
  }

}










