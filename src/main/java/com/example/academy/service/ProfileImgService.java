package com.example.academy.service;

import com.example.academy.domain.ProfileImage;
import com.example.academy.dto.ProfileImgDTO;
import com.example.academy.repository.ProfileImgRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public class ProfileImgService {

  private ProfileImgRepository profileImgRepository;

  @Autowired
  public ProfileImgService(ProfileImgRepository profileImgRepository) {
    this.profileImgRepository = profileImgRepository;
  }




  public List<ProfileImage> getAllProfileImg() {
    return profileImgRepository.findAll();
  }

  public ProfileImage updateProfileImage(Long id, ProfileImgDTO profileImgDTO) {
    ProfileImage updateProfileImg = profileImgRepository.findById(id).orElseThrow();
    updateProfileImg.setPictureUrl(profileImgDTO.getPictureUrl());

    return profileImgRepository.save(updateProfileImg);
  }

  public void deleteProfileImage(Long id) {
    profileImgRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 프로필 이미지를 찾을 수 없습니다. " + id));
    profileImgRepository.deleteById(id);
  }
}
