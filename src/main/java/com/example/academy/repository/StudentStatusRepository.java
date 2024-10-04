package com.example.academy.repository;

import com.example.academy.domain.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentStatusRepository extends JpaRepository<StudentStatus, Long> {

  public StudentStatus findByMemberId(Long memberId);

}
