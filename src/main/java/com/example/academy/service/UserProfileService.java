package com.example.academy.service;


import com.example.academy.domain.Member;
import com.example.academy.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

  @Autowired
  private UserRepository userRepository;

  public Optional<Member> getUserProfileById(Long id) {
    return userRepository.findById(id);
  }

  public Optional<Member> getUserProfileByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }

  public Member createOrUpdateUserProfile(Member member) {
    return userRepository.save(member);
  }
}