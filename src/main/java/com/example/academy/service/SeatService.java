package com.example.academy.service;

import com.example.academy.domain.mysql.Course;
import com.example.academy.domain.mysql.Member;
import com.example.academy.domain.mysql.MemberType;
import com.example.academy.domain.mysql.Seat;
import com.example.academy.dto.member.CustomUserDetails;
import com.example.academy.dto.member.MemberDTO;
import com.example.academy.dto.seat.SeatUpdateDTO;
import com.example.academy.dto.seat.SeatListDTO;
import com.example.academy.exception.common.NotFoundException;
import com.example.academy.repository.mysql.CourseRepository;
import com.example.academy.repository.mysql.MemberTypeRepository;
import com.example.academy.repository.mysql.SeatRepository;
import com.example.academy.repository.mysql.MemberRepository;
import java.util.Comparator;
import java.util.stream.IntStream;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatService {

  private final SeatRepository seatRepository;
  private final MemberRepository memberRepository;
  private final MemberTypeRepository memberTypeRepository;
  private final CourseRepository courseRepository;

  private static final String TEACHER_SEAT_NUMBER = "T";

  private final AuthService authService;

  @Autowired
  public SeatService(SeatRepository seatRepository, MemberRepository memberRepository, MemberTypeRepository memberTypeRepository, CourseRepository courseRepository, AuthService authService) {
    this.seatRepository = seatRepository;
    this.memberRepository = memberRepository;
    this.authService = authService;
    this.memberTypeRepository = memberTypeRepository;
    this.courseRepository = courseRepository;
  }

  public List<SeatListDTO> getAllSeat() {
    List<Seat> seatList = seatRepository.findAll();
    List<SeatListDTO> seatListDTO = new ArrayList<>();

    // 나머지 좌석 추가 (문자 및 숫자 순으로 정렬)
    seatList.stream()
        .filter(seat -> !TEACHER_SEAT_NUMBER.equals(seat.getSeatNumber()))
        .sorted((s1, s2) -> {
          String seatNumber1 = s1.getSeatNumber();
          String seatNumber2 = s2.getSeatNumber();

          // 문자 부분 추출
          String prefix1 = seatNumber1.replaceAll("\\d", "");
          String prefix2 = seatNumber2.replaceAll("\\d", "");

          // 숫자 부분 추출
          int number1 = Integer.parseInt(seatNumber1.replaceAll("\\D", ""));
          int number2 = Integer.parseInt(seatNumber2.replaceAll("\\D", ""));

          // 문자 부분이 같다면 숫자 부분으로 정렬, 다르다면 문자 부분으로 정렬
          int prefixComparison = prefix1.compareTo(prefix2);
          return prefixComparison != 0 ? prefixComparison : Integer.compare(number1, number2);
        })
        .forEach(seat -> seatListDTO.add(convertSeatToDTO(seat)));

    return seatListDTO;
  }

  @Transactional
  public Optional<SeatListDTO> assignSeatToMember(SeatUpdateDTO seatUpdateDTO) {
    CustomUserDetails user = authService.getAuthenticatedUser();
    Member member = memberRepository.findById(user.getUserId()).orElseThrow(() -> new NotFoundException("해당 회원이 없습니다."));

    System.out.println("Received seatDTO: " + seatUpdateDTO);

    Long seatId = seatUpdateDTO.getId();

    System.out.println("Assigning seat with ID: " + seatId + " to member with ID: " + member.getId());

    System.out.println("Found member: " + member);

    Optional<Seat> occupiedSeat = seatRepository.findByMemberId(member.getId());
    if (occupiedSeat.isPresent()) {
      System.out.println("Member already occupies another seat: " + occupiedSeat.get());
      return Optional.empty();
    }

    Seat seat = seatRepository.findById(seatId)
        .orElseThrow(() -> {
          System.out.println("Seat not found with ID: " + seatId);
          return new ResponseStatusException(HttpStatus.NOT_FOUND, "Seat not found");
        });

    System.out.println("Found seat: " + seat);

    seat.setMember(member);
    Seat savedSeat = seatRepository.save(seat);
    System.out.println("Seat successfully assigned to member: " + savedSeat);

    return Optional.of(convertSeatToDTO(savedSeat));
  }

  private SeatListDTO assignOrUpdateTeacherSeat(Member teacher) {
    System.out.println("Finding or creating seat for teacher");

    Seat teacherSeat = seatRepository.findBySeatNumber(TEACHER_SEAT_NUMBER)
        .orElseGet(() -> {
          Seat newSeat = new Seat();
          newSeat.setSeatNumber(TEACHER_SEAT_NUMBER);
          newSeat.setIsExist(true);
          newSeat.setIsOnline(false);
          return seatRepository.save(newSeat);
        });

    if (teacherSeat.getMember() != null) {
      Member oldTeacher = teacherSeat.getMember();
      System.out.println("Teacher seat already occupied by " + oldTeacher.getMemberId() + ", reassigning...");

      MemberType studentRoleType = memberTypeRepository.findByType("ROLE_STUDENT")
          .orElseThrow(() -> new IllegalArgumentException("ROLE_STUDENT 타입이 없습니다."));

      oldTeacher.setMemberType(studentRoleType);
      memberRepository.save(oldTeacher);
      teacherSeat.setMember(null);
    }

    teacherSeat.setMember(teacher);
    Seat savedTeacherSeat = seatRepository.save(teacherSeat);
    System.out.println("Teacher seat successfully reassigned to " + teacher.getMemberId());

    return convertSeatToDTO(savedTeacherSeat);
  }

  public SeatListDTO removeMemberFromSeat(Long seatId) {

    Seat seat = seatRepository.findById(seatId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seat not found"));

    seat.setMember(null);
    return convertSeatToDTO(seatRepository.save(seat));
  }

  private SeatListDTO convertSeatToDTO(Seat seat) {
    Member member = seat.getMember();
    MemberDTO memberDTO = null;

    if (member != null) {
      memberDTO = new MemberDTO(
          member.getId(),
          member.getMemberId(),
          member.getName(),
          member.getEmail(),
          member.getPhone(),
          member.getMemberType(),
          member.getAvatarImage() != null ? member.getAvatarImage().getAvatarImageUrl() : null,
          member.getGitUrl(),
          member.getBio()
      );
    }
    return new SeatListDTO(seat.getId(), seat.getSeatNumber(), seat.getIsExist(), seat.getIsOnline(), memberDTO);
  }

  @Transactional
  public SeatListDTO assignTeacherSeat(String memberId) {
    Member member = memberRepository.findByMemberId(memberId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));

    if (!member.getMemberType().equals("ROLE_TEACHER")) {
      throw new IllegalStateException("강사만 사용 가능 합니다.");
    }

    return assignOrUpdateTeacherSeat(member);
  }

  @Transactional
  public List<SeatListDTO> registerSeats(int seatCount, Long courseId) {
    // 등록 시, 해당 강의에 대한 기존 좌석 모두 삭제
    Course course = courseRepository.findById(courseId)
        .orElseThrow(() -> new EntityNotFoundException("Course not found"));

    List<Seat> existingSeats = seatRepository.findByCourse(course);

    // 기존 좌석 모두 삭제 (멤버 포함)
    seatRepository.deleteAll(existingSeats);

    List<SeatListDTO> createdSeats = new ArrayList<>();
    for (int i = 1; i <= seatCount; i++) {
      Seat seat = new Seat();
      seat.setSeatNumber(String.valueOf(i)); // 좌석 번호를 1부터 시작하여 순차적으로 설정
      seat.setIsExist(true);
      seat.setIsOnline(false);
      seat.setCourse(course);  // 코스 설정
      Seat savedSeat = seatRepository.save(seat);
      createdSeats.add(convertSeatToDTO(savedSeat));
    }

    return createdSeats;
  }

  @Transactional
  public void deleteAllSeatsByCourse(Long courseId) {
    // 해당 코스에 있는 모든 좌석 삭제
    Course course = courseRepository.findById(courseId)
        .orElseThrow(() -> new EntityNotFoundException("Course not found"));

    List<Seat> seats = seatRepository.findByCourse(course);

    // 좌석 삭제
    seatRepository.deleteAll(seats);
  }

  public List<SeatListDTO> getSeatsByCourse(Long courseId) {
    Course course = courseRepository.findById(courseId)
        .orElseThrow(() -> new NotFoundException("Course not found"));

    List<Seat> seats = seatRepository.findByCourse(course);
    return seats.stream()
        .map(this::convertSeatToDTO)
        .collect(Collectors.toList());
  }



}