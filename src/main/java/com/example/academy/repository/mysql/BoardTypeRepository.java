package com.example.academy.repository.mysql;

import com.example.academy.domain.mysql.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTypeRepository extends JpaRepository<BoardType, Long> {

}