package com.example.academy.controller;

import com.example.academy.domain.UserProfile;
import com.example.academy.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller // RestController 대신 Controller로 변경
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<UserProfile> getUserProfile(@PathVariable String id) {
        return userProfileService.getUserProfileById(id);
    }

    @PostMapping("/update")
    public ResponseEntity<UserProfile> updateUserProfile(@RequestBody UserProfile userProfile) {
        UserProfile updatedProfile = userProfileService.createOrUpdateUserProfile(userProfile);
        return ResponseEntity.ok(updatedProfile); // 데이터 저장 후 응답
    }
}