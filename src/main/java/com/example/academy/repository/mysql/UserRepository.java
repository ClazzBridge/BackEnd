package com.example.academy.repository.mysql;

import com.example.academy.domain.mysql.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Member, Long> {

  // 사용자 이름으로만 조회
  Optional<Member> findByName(String userName);

  Optional<Member> findByMemberId(String memberId);


  boolean existsByMemberId(String memberId);

  boolean existsByEmail(String email);

  boolean existsByPhone(String phone);

}
