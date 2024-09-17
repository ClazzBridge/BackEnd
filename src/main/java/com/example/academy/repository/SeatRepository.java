package com.example.academy.repository;

import com.example.academy.domain.Seat;
import com.example.academy.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

}
