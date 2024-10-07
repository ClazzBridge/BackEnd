package com.example.academy.service;

import com.example.academy.domain.Member;
import com.example.academy.domain.Seat;
import com.example.academy.dto.MemberDTO;
import com.example.academy.dto.SeatDTO;
import com.example.academy.dto.StudentStatusDTO;
import com.example.academy.repository.MemberRepository;
import com.example.academy.repository.SeatRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

  private SeatRepository seatRepository;
  private MemberRepository memberRepository;

  @Autowired
  public SeatService(SeatRepository seatRepository, MemberRepository memberRepository) {
    this.seatRepository = seatRepository;
    this.memberRepository = memberRepository;
  }

  // 좌석 조회
  public List<SeatDTO> getAllSeat() {
    List<Seat> seatList = seatRepository.findAll();

    List<SeatDTO> seatDTOList = new ArrayList<>();

    for (Seat seat : seatList) {
      SeatDTO seatDTO = convertSeatToDTO(seat);
      seatDTOList.add(seatDTO);
    }
    return seatDTOList;
  }

  public SeatDTO assignMemberToSeatById(Long id, String memberId) {
    Seat existingSeat = seatRepository.findById(id).orElseThrow(() ->
        new EntityNotFoundException("Seat not found with id: " + id));

    Member member = memberRepository.findByMemberId(memberId).orElseThrow(() ->
        new EntityNotFoundException("Member not found with memberId: " + memberId));

    existingSeat.setMember(member); // 멤버를 좌석에 할당
    Seat savedSeat = seatRepository.save(existingSeat);

    // 좌석과 멤버 엔티티를 DTO로 변환하여 반환
    return convertSeatToDTO(savedSeat);
  }

  // Seat 엔티티를 SeatDTO로 변환하는 메서드
  private SeatDTO convertSeatToDTO(Seat seat) {
    Member member = seat.getMember();
    MemberDTO memberDTO = null;

    if (member != null) {

      StudentStatusDTO studentStatusDTO = null;
      if (member.getStudentStatus() != null) {
        studentStatusDTO = new StudentStatusDTO(member.getStudentStatus());
      }
      memberDTO = new MemberDTO(
          member.getMemberId(),
          member.getName(),
          member.getEmail(),
          member.getPhone(),
          member.getMemberType(),
          member.getProfileImage(),
          member.getGitUrl(),
          member.getBio(),
          studentStatusDTO // 추가된 studentStatusDTO 전달
      );
    }

    // SeatDTO로 변환
    return new SeatDTO(seat.getId(), seat.getSeatNumber(), seat.getIsExist(), seat.getIsOnline(), memberDTO);
  }

  public Seat removeMemberFromSeat(Long seatId) {
    Seat existingSeat = seatRepository.findById(seatId).orElseThrow(() ->
        new EntityNotFoundException("Seat not found with id: " + seatId));

    // 좌석의 멤버를 null로 설정하여 멤버 정보 제거
    existingSeat.setMember(null);

    return seatRepository.save(existingSeat);
  }

}