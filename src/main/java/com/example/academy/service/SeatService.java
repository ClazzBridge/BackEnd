package com.example.academy.service;

import com.example.academy.domain.Seat;
import com.example.academy.dto.SeatDTO;
import com.example.academy.repository.SeatRepository;
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

  public void deleteSeat(Long id) {
    Seat deleteSeat = seatRepository.findById(id).orElseThrow(() ->
        new IllegalArgumentException("Seat not found with id: " + id));
    seatRepository.delete(deleteSeat);
  }


}
