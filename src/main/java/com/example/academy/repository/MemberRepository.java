package com.example.academy.repository;

import com.example.academy.domain.Member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
  // 이메일을 기준으로 사용자를 찾는 메서드 추가
  Optional<Member> findByEmail(String email);
}



