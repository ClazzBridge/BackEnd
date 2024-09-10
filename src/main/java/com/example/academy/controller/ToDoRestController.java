package com.example.academy.controller;

import com.example.todo_crud.dto.LoginRequestDTO;
import com.example.todo_crud.dto.LoginResponseDTO;
import com.example.todo_crud.service.UserListService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/sign")
public class ToDoRestController {

    private final UserListService userListService;

    public ToDoRestController(UserListService userListService) {
        this.userListService = userListService;
    }

    @PostMapping
    public Optional<LoginResponseDTO> login(@RequestBody LoginRequestDTO req) {

        System.out.println(userListService.login(req.getUserName(), req.getPassword()));
        return userListService.login(req.getUserName(), req.getPassword());
    }
}
