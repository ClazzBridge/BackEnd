package com.example.academy.service;

import com.example.academy.domain.UserList;
import com.example.academy.repository.UserListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserListService {

  private UserListRepository userListRepository;

  @Autowired
  public UserListService(UserListRepository userListRepository) {
    this.userListRepository = userListRepository;
  }

  public UserList getUserById(Long id) {
    return userListRepository.findById(id).orElseThrow();
  }

  public UserList findByEmail(String email) {
    return userListRepository.findByEmail(email);
  }
}
