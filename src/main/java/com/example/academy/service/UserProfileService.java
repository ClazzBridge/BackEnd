package com.example.academy.service;

import com.example.academy.domain.UserProfile;
import com.example.academy.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile createOrUpdateUserProfile(UserProfile userProfile) {
        System.out.println("변경된 아이디: " + userProfile.getId());
        return userProfileRepository.save(userProfile);
    }

    public Optional<UserProfile> getUserProfileById(String id) {
        return userProfileRepository.findById(id);
    }
}