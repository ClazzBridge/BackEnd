package com.example.academy.service;


import com.example.academy.domain.mysql.Course;
import com.example.academy.domain.mysql.Member;
import com.example.academy.domain.mysql.StudentCourse;
import com.example.academy.dto.member.GetDetailMemberDTO;
import com.example.academy.dto.member.GetMemberDTO;
import com.example.academy.jwt.JwtUtil;
import com.example.academy.repository.mysql.CourseRepository;
import com.example.academy.repository.mysql.StudentCourseRepository;
import com.example.academy.repository.mysql.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class MemberListService {

  @Autowired
  private final MemberRepository memberRepository;
  private final StudentCourseRepository studentCourseRepository;
  private final CourseRepository courseRepository;


  public MemberListService(MemberRepository memberRepository,
      StudentCourseRepository studentCourseRepository, CourseRepository courseRepository) {
    this.memberRepository = memberRepository;
    this.studentCourseRepository = studentCourseRepository;
    this.courseRepository = courseRepository;
  }


  public GetDetailMemberDTO getMemberWithCourseInfo(Long memberId) {
    // Member 조회
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new NoSuchElementException("Member not found with ID: " + memberId));

    String courseName = "";
    if(member.getMemberType().equals(MemberType.ROLE_STUDENT)) {
      // Member가 수강한 StudentCourse 조회
      List<StudentCourse> courses = studentCourseRepository.findByStudent(member);
       courseName = courses.stream()
          .findFirst()  // 여러 개일 경우 첫 번째 코스를 선택
          .map(studentCourse -> studentCourse.getCourse().getTitle())  // Course 이름 가져오기
          .orElse("No course enrolled");
    } else if(member.getMemberType().equals(MemberType.ROLE_TEACHER)){
      List<Course> courses = courseRepository.findByInstructor(member);
       courseName = courses.stream()
          .findFirst()  // 여러 개일 경우 첫 번째 코스를 선택
          .map(Course -> Course.getTitle())  // Course 이름 가져오기
          .orElse("No course enrolled");
    }else {
      // 멤버 타입이 STUDENT 또는 TEACHER가 아니면 예외 발생
      throw new UnsupportedOperationException("Unsupported member type for ID: " + memberId);
    }

    // GetMemberDTO 생성 및 값 설정
    GetDetailMemberDTO getDetailMemberDTO = new GetDetailMemberDTO(
        member.getId(),
        member.getMemberId(),
        member.getPassword(),
        member.getName(),
        member.getEmail(),
        member.getPhone(),
        member.getMemberType(),
        member.getProfileImage(),
        courseName
    );

    return getDetailMemberDTO;
  }
  public List<GetMemberDTO> getAllMembersWithCourses() {
    List<Member> members = memberRepository.findAll(); // 전체 멤버 조회
    List<GetMemberDTO> memberDTOs = new ArrayList<>();
    String courseName;
    // 각 멤버에 대해 코스 정보 조회 및 DTO로 변환
    for (Member member : members) {
      if (member.getMemberType().equals(MemberType.ROLE_STUDENT)) {
        List<StudentCourse> studentCourses = studentCourseRepository.findByStudent(member);

        // 첫 번째 수강 코스의 이름을 가져오거나, 없으면 빈 문자열 반환
         courseName = studentCourses.stream()
            .findFirst()  // 여러 개일 경우 첫 번째 코스를 선택
            .map(studentCourse -> studentCourse.getCourse().getTitle())
            .orElse("No course enrolled");
      }else if (member.getMemberType().equals(MemberType.ROLE_TEACHER)) {
        List<Course> courses = courseRepository.findByInstructor(member);

        // 첫 번째 수강 코스의 이름을 가져오거나, 없으면 빈 문자열 반환
         courseName = courses.stream()
            .findFirst()  // 여러 개일 경우 첫 번째 코스를 선택
            .map(studentCourse -> studentCourse.getTitle())
            .orElse("No course enrolled");
      }else {
        continue;
      }
      // GetMemberDTO 생성 및 값 설정
      GetMemberDTO dto = new GetMemberDTO(
          member.getId(),
          member.getMemberId(),
          member.getPassword(),
          member.getName(),
          member.getEmail(),
          member.getPhone(),
          member.getMemberType(),
          member.getProfileImage(),
          courseName
      );

      // DTO 리스트에 추가
      memberDTOs.add(dto);
    }

    return memberDTOs;
  }

  public void deleteMember(Long id) {
    memberRepository.deleteById(id);
  }
}
