package com.example.academy.repository;


import com.example.academy.domain.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    // 기본적인 CRUD 메서드는 MongoRepository가 제공
}