package com.example.academy.service;

import com.example.academy.domain.mysql.AvatarImage;
import com.example.academy.domain.mysql.Course;
import com.example.academy.domain.mysql.Member;
import com.example.academy.domain.mysql.StudentCourse;
import com.example.academy.domain.mysql.MemberType;
import com.example.academy.dto.member.MemberSignUpDTO;
import com.example.academy.dto.member.MemberUpdateDTO;
import com.example.academy.repository.mysql.CourseRepository;
import com.example.academy.repository.mysql.MemberTypeRepository;
import com.example.academy.repository.mysql.StudentCourseRepository;
import com.example.academy.repository.mysql.MemberRepository;
import com.example.academy.repository.mysql.AvatarImageRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberManageService {

  private final MemberRepository memberRepository;
  private final MemberTypeRepository memberTypeRepository;
  private final AvatarImageRepository avatarImageRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final CourseRepository courseRepository;
  private final StudentCourseRepository studentCourseRepository;

  public MemberManageService(MemberRepository memberRepository, MemberTypeRepository memberTypeRepository,
      AvatarImageRepository avatarImageRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
      CourseRepository courseRepository, StudentCourseRepository studentCourseRepository) {
    this.memberRepository = memberRepository;
    this.memberTypeRepository = memberTypeRepository;
    this.avatarImageRepository = avatarImageRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.courseRepository = courseRepository;
    this.studentCourseRepository = studentCourseRepository;
  }

  public ResponseEntity<String> signUp(MemberSignUpDTO memberSignUpDTO) {

    String memberId = memberSignUpDTO.getMemberId();
    String password = memberSignUpDTO.getPassword();
    String name = memberSignUpDTO.getName();
    String email = memberSignUpDTO.getEmail();
    String phone = memberSignUpDTO.getPhone();
    String title = memberSignUpDTO.getCourseTitle();

    Optional<MemberType> memberType = memberTypeRepository.findByType(memberSignUpDTO.getMemberType().getType());
    if (memberType.isEmpty()) {
      throw new RuntimeException("Invalid member type provided");
    }

    boolean isExistMember = memberRepository.existsByMemberId(memberId);
    boolean isExistEmail = memberRepository.existsByEmail(email);
    boolean isExistCourseTitle = courseRepository.existsByTitle(title);

    if (isExistMember || isExistEmail || !isExistCourseTitle) {
      String errorMessage = "";
      if (isExistMember) {
        errorMessage += "memberId 중복. ";
      }
      if (isExistEmail) {
        errorMessage += "email 중복. ";
      }
      if (!isExistCourseTitle) {
        errorMessage += "강의명 없음. ";
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
    data.setMemberType(memberType.get());
    // ProfileImage 테이블의 총 레코드 수 가져오기
    long count = avatarImageRepository.count();

    // 1부터 count까지의 랜덤 ID 생성
    int randomId = (int) (Math.random() * count) + 1;

    // 랜덤 ID에 해당하는 ProfileImage 가져오기
    AvatarImage avatarImage = avatarImageRepository.findById((long) randomId)
        .orElseThrow(() -> new RuntimeException("ProfileImage not found"));

    // member 객체에 profileImage 설정
    data.setAvatarImage(avatarImage);

    Member saveMember = memberRepository.save(data);

    Long id = saveMember.getId();

    // 과정명 추가
    // 수강생은 student_course에 데이터 추가
    if (memberType.get().getType().equals("ROLE_STUDENT")) {
      Optional<Course> course = courseRepository.findByTitle(title);
      StudentCourse studentCourse = new StudentCourse();

      studentCourse.setCourse(course.get());
      studentCourse.setStudent(saveMember);

      studentCourseRepository.save(studentCourse);

    } else {
      Optional<Course> course = courseRepository.findByTitle(title);
      course.get().setInstructor(saveMember);
      courseRepository.save(course.get());
    }

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body("회원가입 성공");
  }

  public ResponseEntity<String> updateMember(MemberUpdateDTO memberUpdateDTO) {
    // 기존 회원 데이터를 id로 조회
    Member member = memberRepository.findById(memberUpdateDTO.getId())
        .orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다."));

    // 본인을 제외한 memberId와 email 중복 체크
    boolean isExistMember = memberRepository.existsByMemberIdAndIdNot(memberUpdateDTO.getMemberId(), member.getId());
    boolean isExistEmail = memberRepository.existsByEmailAndIdNot(memberUpdateDTO.getEmail(), member.getId());

    if (isExistMember || isExistEmail) {
      String errorMessage = "";
      if (isExistMember) {
        errorMessage += "memberId 중복. ";
      }
      if (isExistEmail) {
        errorMessage += "email 중복. ";
      }
      System.out.println(errorMessage);
      throw new DataIntegrityViolationException(errorMessage);
    }

    // DTO의 값으로 기존 데이터를 업데이트
    member.setMemberId(memberUpdateDTO.getMemberId());
    member.setPassword(bCryptPasswordEncoder.encode(memberUpdateDTO.getPassword())); // 비밀번호 인코딩
    member.setName(memberUpdateDTO.getName());
    member.setEmail(memberUpdateDTO.getEmail());
    member.setPhone(memberUpdateDTO.getPhone());

    Optional<MemberType> memberType = memberTypeRepository.findByType(memberUpdateDTO.getMemberType().getType());
    if (memberType.isEmpty()) {
      throw new RuntimeException("Invalid member type provided");
    }
    member.setMemberType(memberType.get());

    String title = memberUpdateDTO.getCourseTitle();

    if (memberUpdateDTO.getMemberType().getType().equals("ROLE_STUDENT")) {
      List<StudentCourse> studentCourses = studentCourseRepository.findByStudent(member);
      if (!studentCourses.isEmpty()) {
        Optional<Course> course = courseRepository.findByTitle(title);
        StudentCourse studentCourse = studentCourses.get(0);
        studentCourse.setCourse(course.get());
        studentCourseRepository.save(studentCourse);
      } else {
        throw new RuntimeException("해당 과정명을 찾을 수 없습니다.");
      }
    } else if (memberUpdateDTO.getMemberType().getType().equals("ROLE_TEACHER")) {
      Optional<Course> course = courseRepository.findByTitle(title);
      if (course.get().getTitle().equals(null)) {
        throw new RuntimeException("해당 과정명은 배정된 강사가 있습니다");
      }
      if (course.isPresent()) {
        List<Course> course1 = courseRepository.findByInstructor(member);
        Course course11 = course1.get(0);
        Course course2 = course.get();
        if (course11.getTitle().equals(course2.getTitle())) {
          System.out.println("동일 강의");
        } else {
          course2.setInstructor(member);
          course11.setInstructor(null);
          courseRepository.save(course11);
          courseRepository.save(course.get());
        }
      } else {
        throw new RuntimeException("해당 과정명을 찾을 수 없습니다.");
      }
    }

    memberRepository.save(member);

    return ResponseEntity.status(HttpStatus.OK).body("회원 정보 업데이트 성공");
  }
}