package com.example.academy.repository.mysql;

import com.example.academy.domain.Vote;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

  Optional<Vote> findByTitle(String title); // 강의실 이름으로 조회

  boolean existsByTitle(String title);

  List<Vote> findAll();

}
