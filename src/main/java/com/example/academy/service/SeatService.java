package com.example.academy.service;

import com.example.academy.domain.mysql.Member;
import com.example.academy.domain.mysql.MemberType;
import com.example.academy.domain.mysql.Seat;
import com.example.academy.dto.member.MemberDTO;
import com.example.academy.dto.seat.SeatDTO;
import com.example.academy.repository.mysql.MemberTypeRepository;
import com.example.academy.repository.mysql.SeatRepository;
import com.example.academy.repository.mysql.MemberRepository;
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

  private static final String TEACHER_SEAT_NUMBER = "T";

  @Autowired
  public SeatService(SeatRepository seatRepository, MemberRepository memberRepository, MemberTypeRepository memberTypeRepository) {
    this.seatRepository = seatRepository;
    this.memberRepository = memberRepository;
    this.memberTypeRepository = memberTypeRepository;
  }

  public List<SeatDTO> getAllSeat() {
    List<Seat> seatList = seatRepository.findAll();
    List<SeatDTO> seatDTOList = new ArrayList<>();

    // 강사 좌석을 먼저 추가
    seatList.stream()
        .filter(seat -> TEACHER_SEAT_NUMBER.equals(seat.getSeatNumber()))
        .findFirst()
        .ifPresent(teacherSeat -> seatDTOList.add(convertSeatToDTO(teacherSeat)));

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
        .forEach(seat -> seatDTOList.add(convertSeatToDTO(seat)));

    return seatDTOList;
  }

  @Transactional
  public Optional<SeatDTO> assignSeatToMember(SeatDTO seatDTO) {
    Long seatId = seatDTO.getId();
    String memberId = seatDTO.getMemberDTO().getMemberId();

    Member member = memberRepository.findByMemberId(memberId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));

    Optional<Seat> occupiedSeat = seatRepository.findByMember_Id(member.getId());
    if (occupiedSeat.isPresent()) {
      return Optional.empty();
    }

    if ("ROLE_TEACHER".equals(member.getMemberType())) {
      return Optional.of(assignOrUpdateTeacherSeat(member));
    }

    Seat seat = seatRepository.findById(seatId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seat not found"));

    seat.setMember(member);
    return Optional.of(convertSeatToDTO(seatRepository.save(seat)));
  }

  private SeatDTO assignOrUpdateTeacherSeat(Member teacher) {
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

      // 데이터베이스에서 ROLE_STUDENT에 해당하는 MemberType을 가져오기
      MemberType studentRoleType = memberTypeRepository.findByType("ROLE_STUDENT")
          .orElseThrow(() -> new IllegalArgumentException("ROLE_STUDENT 타입이 없습니다."));

      oldTeacher.setMemberType(studentRoleType);
      memberRepository.save(oldTeacher);
      teacherSeat.setMember(null);
    }

    teacherSeat.setMember(teacher);
    return convertSeatToDTO(seatRepository.save(teacherSeat));
  }

  public SeatDTO removeMemberFromSeat(Long seatId) {
    Seat seat = seatRepository.findById(seatId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seat not found"));

    seat.setMember(null);
    return convertSeatToDTO(seatRepository.save(seat));
  }

  private SeatDTO convertSeatToDTO(Seat seat) {
    Member member = seat.getMember();
    MemberDTO memberDTO = null;

    if (member != null) {
      memberDTO = new MemberDTO(
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
    return new SeatDTO(seat.getId(), seat.getSeatNumber(), seat.getIsExist(), seat.getIsOnline(), memberDTO);
  }

  @Transactional
  public SeatDTO assignTeacherSeat(String memberId) {
    Member member = memberRepository.findByMemberId(memberId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));

    if (!member.getMemberType().equals("ROLE_TEACHER")) {
      throw new IllegalStateException("강사만 사용 가능 합니다.");
    }

    return assignOrUpdateTeacherSeat(member);
  }

  @Transactional
  public List<SeatDTO> registerSeats(int seatCount) {
    if (seatCount <= 0) {
      throw new IllegalArgumentException("좌석 수는 1 이상이어야 합니다.");
    }

    List<SeatDTO> createdSeats = new ArrayList<>();

    // 강사 좌석 처리
    Seat teacherSeat = seatRepository.findBySeatNumber(TEACHER_SEAT_NUMBER)
        .orElseGet(() -> {
          Seat newSeat = new Seat();
          newSeat.setSeatNumber(TEACHER_SEAT_NUMBER);
          newSeat.setIsExist(true);
          newSeat.setIsOnline(false);
          return seatRepository.save(newSeat);
        });
    createdSeats.add(convertSeatToDTO(teacherSeat));

    // 기존 일반 좌석 삭제
    seatRepository.deleteAll(
        seatRepository.findAll().stream()
            .filter(seat -> !TEACHER_SEAT_NUMBER.equals(seat.getSeatNumber()))
            .collect(Collectors.toList())
    );

    // 새 좌석 생성
    for (int i = 1; i <= seatCount; i++) {
      Seat seat = new Seat();
      seat.setSeatNumber(String.valueOf(i));
      seat.setIsExist(true);
      seat.setIsOnline(false);
      Seat savedSeat = seatRepository.save(seat);
      createdSeats.add(convertSeatToDTO(savedSeat));
    }

    return createdSeats;
  }

  @Transactional
  public List<SeatDTO> modifySeats(int newSeatCount) {
    if (newSeatCount < 0) {
      throw new IllegalArgumentException("좌석 수는 0 이상이어야 합니다.");
    }

    List<Seat> currentSeats = seatRepository.findAll();

    // 강사 좌석 찾기
    Seat teacherSeat = currentSeats.stream()
        .filter(seat -> TEACHER_SEAT_NUMBER.equals(seat.getSeatNumber()))
        .findFirst()
        .orElse(null);

    // 학생 좌석만 필터링
    List<Seat> studentSeats = currentSeats.stream()
        .filter(seat -> !TEACHER_SEAT_NUMBER.equals(seat.getSeatNumber()))
        .collect(Collectors.toList());

    int currentStudentSeatCount = studentSeats.size();

    // 좌석 번호 재할당
    for (int i = 0; i < Math.min(newSeatCount, currentStudentSeatCount); i++) {
      studentSeats.get(i).setSeatNumber(String.valueOf(i + 1));
      seatRepository.save(studentSeats.get(i));
    }

    if (newSeatCount > currentStudentSeatCount) {
      // 좌석 추가
      for (int i = currentStudentSeatCount + 1; i <= newSeatCount; i++) {
        Seat seat = new Seat();
        seat.setSeatNumber(String.valueOf(i));
        seat.setIsExist(true);
        seat.setIsOnline(false);
        seatRepository.save(seat);
      }
    } else if (newSeatCount < currentStudentSeatCount) {
      // 좌석 제거 (사용 중인 좌석은 제거하지 않음)
      List<Seat> seatsToRemove = studentSeats.subList(newSeatCount, currentStudentSeatCount);
      for (Seat seat : seatsToRemove) {
        if (seat.getMember() == null) {
          seatRepository.delete(seat);
        } else {
          // 사용 중인 좌석은 번호만 제거
          seat.setSeatNumber(null);
          seatRepository.save(seat);
        }
      }
    }

    // 강사 좌석 처리
    if (teacherSeat != null) {
      teacherSeat.setSeatNumber(TEACHER_SEAT_NUMBER);
      seatRepository.save(teacherSeat);
    } else {
      // 강사 좌석이 없으면 생성
      Seat newTeacherSeat = new Seat();
      newTeacherSeat.setSeatNumber(TEACHER_SEAT_NUMBER);
      newTeacherSeat.setIsExist(true);
      newTeacherSeat.setIsOnline(false);
      seatRepository.save(newTeacherSeat);
    }

    return getAllSeat();
  }
}