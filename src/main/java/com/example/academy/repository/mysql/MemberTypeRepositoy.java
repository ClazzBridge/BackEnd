package com.example.academy.repository.mysql;

import com.example.academy.domain.mysql.MemberType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTypeRepositoy extends JpaRepository<MemberType, Long> {


  Optional<MemberType> findByType(String type);
}
