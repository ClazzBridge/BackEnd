package com.example.academy.repository;

import com.example.academy.domain.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileImgRepository extends JpaRepository<ProfileImage, Long> {

}
