package com.example.academy.controller;

import com.example.academy.domain.ProfileImage;
import com.example.academy.dto.ProfileImgDTO;
import com.example.academy.service.ProfileImgService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profileimg")
public class ProfileImgController {

  private ProfileImgService profileImgService;


  @Autowired
  public ProfileImgController(ProfileImgService profileImgService) {
    this.profileImgService = profileImgService;
  }



  // 프로필 이미지
  @GetMapping("/")
  public ResponseEntity<List<ProfileImage>> getAllProfileImage() {
    List<ProfileImage> listProfileImage = profileImgService.getAllProfileImg();
    return ResponseEntity.ok(listProfileImage);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProfileImage> updateProfileImage(@PathVariable Long id, @RequestBody ProfileImgDTO profileImgDTO) {
    ProfileImage updateProfileImage = profileImgService.updateProfileImage(id, profileImgDTO);
    return ResponseEntity.ok(updateProfileImage);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProfileImage(@PathVariable Long id) {
    profileImgService.deleteProfileImage(id);
    return ResponseEntity.noContent().build();
  }
}
