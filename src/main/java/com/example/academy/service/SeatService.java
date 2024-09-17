package com.example.academy.service;

import com.example.academy.domain.ProfileImage;
import com.example.academy.domain.Seat;
import com.example.academy.domain.User;
import com.example.academy.dto.ProfileImgDTO;
import com.example.academy.dto.SeatDTO;
import com.example.academy.repository.SeatRepository;
import com.example.academy.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

  private SeatRepository seatRepository;

  @Autowired
  public SeatService(SeatRepository seatRepository) {
    this.seatRepository = seatRepository;
  }


  public Seat createSeat(SeatDTO seatDTO) {
    String seatNumber = seatDTO.getSeatNumber();

    Seat seat = new Seat();
    seat.setSeatNumber(seatNumber);

    return seatRepository.save(seat);
  }

  public List<Seat> getAllSeat() {
    return seatRepository.findAll();
  }

  public Seat updateSeat(Long id, SeatDTO seatDTO) {
    Seat existingSeat = seatRepository.findById(id).orElseThrow();
    existingSeat.setSeatNumber(seatDTO.getSeatNumber());

    return seatRepository.save(existingSeat);
  }


}
