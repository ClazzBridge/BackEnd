package com.example.academy.service;

import com.example.academy.domain.Member;
import com.example.academy.domain.Seat;
import com.example.academy.dto.MemberDTO;
import com.example.academy.dto.SeatDTO;
import com.example.academy.dto.StudentStatusDTO;
import com.example.academy.repository.MemberRepository;
import com.example.academy.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

  private final SeatRepository seatRepository;
  private final MemberRepository memberRepository;

  @Autowired
  public SeatService(SeatRepository seatRepository, MemberRepository memberRepository) {
    this.seatRepository = seatRepository;
    this.memberRepository = memberRepository;
  }

  public List<SeatDTO> getAllSeat() {
    List<Seat> seatList = seatRepository.findAll();
    List<SeatDTO> seatDTOList = new ArrayList<>();

    for (Seat seat : seatList) {
      seatDTOList.add(convertSeatToDTO(seat));
    }
    return seatDTOList;
  }

  public Optional<SeatDTO> assignSeatToMember(Long seatId, String memberId) {
    Member member = memberRepository.findByMemberId(memberId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));

    Optional<Seat> occupiedSeat = seatRepository.findByMember_Id(member.getId());
    if (occupiedSeat.isPresent()) {
      return Optional.empty();
    }

    Seat seat = seatRepository.findById(seatId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seat not found"));

    seat.setMember(member);
    return Optional.of(convertSeatToDTO(seatRepository.save(seat)));
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
      StudentStatusDTO studentStatusDTO = member.getStudentStatus() != null
          ? new StudentStatusDTO(member.getStudentStatus().getId(),
          member.getStudentStatus().getIsUnderstanding(),
          member.getStudentStatus().getIsHandRaised())
          : null;
      memberDTO = new MemberDTO(member.getMemberId(), member.getName(), member.getEmail(),
          member.getPhone(), member.getMemberType(), member.getProfileImage(),
          member.getGitUrl(), member.getBio(), studentStatusDTO);
    }
    return new SeatDTO(seat.getId(), seat.getClassroom(), seat.getSeatNumber(), seat.getIsExist(), seat.getIsOnline(), memberDTO);
  }
}