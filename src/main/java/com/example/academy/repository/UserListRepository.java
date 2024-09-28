package com.example.academy.repository;

import com.example.academy.domain.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserListRepository extends JpaRepository<UserList, Long> {
  // 이메일을 기준으로 사용자를 찾는 메서드 추가
  UserList findByEmail(String email); // 이메일로 사용자 찾기
}


