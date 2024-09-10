package com.example.academy.service;

import com.example.todo_crud.domain.UserList;
import com.example.todo_crud.dto.LoginResponseDTO;
import com.example.todo_crud.jwt.JwtUtil;
import com.example.todo_crud.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserListService 클래스는 사용자 관련 서비스 로직을 처리합니다.
 * 이 클래스는 사용자 인증 및 JWT 토큰 생성을 담당합니다.
 */
@Service
public class UserListService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * UserListService의 생성자.
     *
     * @param userRepository   사용자 정보를 저장하고 검색하는 레포지토리
     * @param passwordEncoder  비밀번호 암호화 및 검증을 위한 암호화기
     * @param jwtUtil          JWT 토큰 생성을 위한 유틸리티
     */
    public UserListService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 사용자 이름과 비밀번호를 사용하여 로그인 인증을 시도합니다.
     *
     * @param userName 사용자의 사용자 이름
     * @param password 사용자의 비밀번호
     * @return 로그인 성공 시 LoginResponseDTO 객체를 포함한 Optional, 실패 시 빈 Optional
     */
    public Optional<LoginResponseDTO> login(String userName, String password) {
        // 사용자 이름으로 사용자 정보를 검색합니다.
        Optional<UserList> user = userRepository.findByUserName(userName);

        // 사용자가 존재하는 경우
        if (user.isPresent()) {
            System.out.println("User found: " + user.get().getUserName()); // 디버깅을 위한 로그

            // 비밀번호가 일치하는지 확인합니다.
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                // 비밀번호가 일치하면 JWT 토큰을 생성합니다. - 10시간
                String token = jwtUtil.createJWT(userName, user.get().getRole().toString(), 60*60*10L);
                // LoginResponseDTO 객체를 생성하고 반환합니다.
                LoginResponseDTO response = new LoginResponseDTO(token);
                return Optional.of(response);
            } else {
                System.out.println("Password does not match"); // 비밀번호 불일치 로그
            }
        } else {
            System.out.println("User not found"); // 사용자 미발견 로그
        }
        // 로그인 실패 시 빈 Optional을 반환합니다.
        return Optional.empty();
    }
}
