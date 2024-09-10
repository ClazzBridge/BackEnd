package com.example.academy.repository;

import com.example.todo_crud.domain.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserList, Long> {

    // 사용자 이름으로만 조회
    Optional<UserList> findByUserName(String userName);

    // 사용자 이름이 존재하는지 여부 확인
    boolean existsByUserName(String userName);
}
