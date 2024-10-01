package com.example.academy.service;

import static com.example.academy.type.MemberType.ROLE_ADMIN;

import com.example.academy.domain.Member;
import com.example.academy.domain.ProfileImage;
import com.example.academy.dto.LoginRequestDTO;
import com.example.academy.repository.UserRepository;
import com.example.academy.type.MemberType;
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

    String memberId = joinDTO.getMemberId();
    String password = joinDTO.getPassword();
    String name = joinDTO.getName();
    String email = joinDTO.getEmail();
    String phone = joinDTO.getPhone();
    MemberType memberType = joinDTO.getMemberType();
    ProfileImage profileImage = joinDTO.getProfileImage();

    boolean isExist = userRepository.existsByMemberId(memberId);
    //동일 아이디 있으면 종료
    if (isExist) {
      return;
    }

    Member data = new Member();

    data.setMemberId(memberId);
    data.setPassword(bCryptPasswordEncoder.encode(password));
    data.setName(name);
    data.setEmail(email);
    data.setPhone(phone);
    data.setMemberType(memberType);
    data.setProfileImage(profileImage);
    data.setMemberType(ROLE_ADMIN); // 임시로 회원가입 시 어드민 권한 부여

    userRepository.save(data);
  }
}










