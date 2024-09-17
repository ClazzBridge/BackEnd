package com.example.academy.service;

import com.example.academy.domain.User;
import com.example.academy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow();
  }
}
