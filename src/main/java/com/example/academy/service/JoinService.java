package com.example.academy.service;

import com.example.todo_crud.domain.UserList;
import com.example.todo_crud.dto.LoginRequestDTO;
import com.example.todo_crud.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(LoginRequestDTO joinDTO) {

        String username = joinDTO.getUserName();
        String password = joinDTO.getPassword();


        boolean isExist = userRepository.existsByUserName(username);
        //동일 아이디 있으면 종료
        if (isExist) {
            return;
        }

        UserList data = new UserList();

        data.setUserName(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole(UserList.UserType.ROLE_ADMIN);

        userRepository.save(data);
    }
}










