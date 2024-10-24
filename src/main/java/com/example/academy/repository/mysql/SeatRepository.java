package com.example.academy.repository.mysql;

import com.example.academy.domain.mysql.Course;
import com.example.academy.domain.mysql.Member;
import com.example.academy.domain.mysql.Seat;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    Optional<Seat> findBySeatNumber(String seatNumber);

    Optional<Seat> findByMemberId(Long memberId);

    Optional<Seat> findByMember_MemberId(String memberId);

    List<Seat> findByCourse(Course course);


    Optional<Seat> findByMember(Member member);
}