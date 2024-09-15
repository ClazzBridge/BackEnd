package com.example.academy.controller;


import com.example.academy.domain.Member;
import com.example.academy.service.UserProfileService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userlist")
public class UserProfileController {

  @Autowired
  private UserProfileService userProfileService;

  @GetMapping("/{userId}")
  public ResponseEntity<Member> getUserProfileById(@PathVariable("userId") Long id) {
    Optional<Member> userProfile = userProfileService.getUserProfileById(id);

    return userProfile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/update")
  public ResponseEntity<Member> updateUserProfile(@RequestBody Member userProfile) {
    Member updatedProfile = userProfileService.createOrUpdateUserProfile(userProfile);
    return ResponseEntity.ok(updatedProfile);
  }
}
