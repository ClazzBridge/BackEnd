package com.example.academy.repository.mysql;


import com.example.academy.domain.mysql.AvatarImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarImageRepository extends JpaRepository<AvatarImage, Long> {


}