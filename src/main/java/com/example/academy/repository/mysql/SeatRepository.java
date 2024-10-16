package com.example.academy.repository.mysql;

import com.example.academy.domain.mysql.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
  Optional<Seat> findBySeatNumber(String seatNumber);

  Optional<Seat> findByMember_Id(Long memberId);
}