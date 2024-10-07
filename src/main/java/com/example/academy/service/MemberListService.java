package com.example.academy.service;


import com.example.academy.domain.Course;
import com.example.academy.domain.Member;
import com.example.academy.domain.StudentCourse;
import com.example.academy.dto.GetMemberDTO;
import com.example.academy.dto.LoginResponseDTO;
import com.example.academy.jwt.JwtUtil;
import com.example.academy.repository.StudentCourseRepository;
import com.example.academy.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserListService 클래스는 사용자 관련 서비스 로직을 처리합니다. 이 클래스는 사용자 인증 및 JWT 토큰 생성을 담당합니다.
 */
@Service
public class MemberListService {

  @Autowired
  private final MemberRepository memberRepository;
  private final StudentCourseRepository studentCourseRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  /**
   * UserListService의 생성자.
   *
   * @param memberRepository  사용자 정보를 저장하고 검색하는 레포지토리
   * @param passwordEncoder 비밀번호 암호화 및 검증을 위한 암호화기
   * @param jwtUtil         JWT 토큰 생성을 위한 유틸리티
   */
  public MemberListService(MemberRepository memberRepository, StudentCourseRepository courseRepository,
      BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
    this.memberRepository = memberRepository;
    this.studentCourseRepository = courseRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
  }

  /**
   * 사용자 이름과 비밀번호를 사용하여 로그인 인증을 시도합니다.
   *
   * @param memberId 사용자의 사용자 이름
   * @param password 사용자의 비밀번호
   * @return 로그인 성공 시 LoginResponseDTO 객체를 포함한 Optional, 실패 시 빈 Optional
   */
  public ResponseEntity<LoginResponseDTO> login(String memberId, String password) {
    // 사용자 이름으로 사용자 정보를 검색합니다.
    Optional<Member> member = memberRepository.findByMemberId(memberId);
    // 사용자가 존재하는 경우
    if (member.isPresent()) {
      Long userId = member.get().getId(); // 이 부분을 isPresent() 확인 후에 위치시킵니다.
      System.out.println("User found: " + member.get().getMemberId()); // 디버깅을 위한 로그

      System.out.println("User found: " + member.get().getMemberId()); // 디버깅을 위한 로그

      // 비밀번호가 일치하는지 확인합니다.
      if (passwordEncoder.matches(password, member.get().getPassword())) {
        // 비밀번호가 일치하면 JWT 토큰을 생성합니다. - 72초
        String accessToken = jwtUtil.createJWT(userId, member.get().getMemberType().toString(),
            60 * 60 * 20L);
        System.out.println("accessToken = " + accessToken);

        // 인증된 사용자에 대한 Refresh Token 생성 - 7200000 - 7200초
        String refreshToken = jwtUtil.createRefreshJWT(userId,
            member.get().getMemberType().toString(),
            60 * 60 * 2000L);
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        // 클라이언트 JavaScript에서 접근 불가
        refreshTokenCookie.setSecure(false);
        // HTTPS에서만 전송 = true(HTTPS 환경에서 권장)
        refreshTokenCookie.setPath("/");       // 쿠키가 유효한 경로
        refreshTokenCookie.setMaxAge(2);  // 2분 유효
        // 쿠키를 응답에 추가
        System.out.println("refreshTokenCookie = " + refreshTokenCookie);

        // LoginResponseDTO 객체를 생성하고 반환합니다.
        LoginResponseDTO response = new LoginResponseDTO(accessToken, refreshTokenCookie);
        return ResponseEntity.ok(response);
      } else {
        System.out.println("Password does not match"); // 비밀번호 불일치 로그
      }
    } else {
      System.out.println("Member not found"); // 사용자 미발견 로그
    }
    // 로그인 실패 시 빈 Optional을 반환합니다.
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 사용자 미발견 시 404 상태 코드 반환
  }

  public GetMemberDTO getMemberWithCourseInfo(Long memberId) {
    // Member 조회
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new NoSuchElementException("Member not found with ID: " + memberId));

    // Member가 수강한 StudentCourse 조회
    List<StudentCourse> studentCourses = studentCourseRepository.findByStudent(member);

    // 첫 번째 수강 코스의 이름을 가져오거나, 없으면 빈 문자열 반환
    String courseName = studentCourses.stream()
        .findFirst()  // 여러 개일 경우 첫 번째 코스를 선택
        .map(studentCourse -> studentCourse.getCourse().getTitle())  // Course 이름 가져오기
        .orElse("No course enrolled");

    // GetMemberDTO 생성 및 값 설정
    GetMemberDTO getMemberDTO = new GetMemberDTO(
        member.getMemberId(),
        member.getPassword(),
        member.getName(),
        member.getEmail(),
        member.getPhone(),
        member.getMemberType(),
        member.getProfileImage(),
        courseName
    );

    return getMemberDTO;
  }
  public List<GetMemberDTO> getAllMembersWithCourses() {
    List<Member> members = memberRepository.findAll(); // 전체 멤버 조회
    List<GetMemberDTO> memberDTOs = new ArrayList<>();

    // 각 멤버에 대해 코스 정보 조회 및 DTO로 변환
    for (Member member : members) {
      List<StudentCourse> studentCourses = studentCourseRepository.findByStudent(member);

      // 첫 번째 수강 코스의 이름을 가져오거나, 없으면 빈 문자열 반환
      String courseName = studentCourses.stream()
          .findFirst()  // 여러 개일 경우 첫 번째 코스를 선택
          .map(studentCourse -> studentCourse.getCourse().getTitle())
          .orElse("No course enrolled");

      // GetMemberDTO 생성 및 값 설정
      GetMemberDTO dto = new GetMemberDTO(
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
