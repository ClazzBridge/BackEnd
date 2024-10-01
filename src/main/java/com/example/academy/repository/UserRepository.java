package com.example.academy.repository;

import com.example.academy.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Member, Long> {

  // 사용자 이름으로만 조회
  Optional<Member> findByName(String userName);

  Optional<Member> findByMemberId(String memberId);

  // 사용자 이름이 존재하는지 여부 확인
  boolean existsByMemberId(String MemberId);
}
