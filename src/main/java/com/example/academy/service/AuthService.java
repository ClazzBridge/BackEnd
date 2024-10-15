package com.example.academy.service;

import com.example.academy.dto.member.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public CustomUserDetails getAuthenticatedUser() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();
    }
}
