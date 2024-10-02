package com.example.academy.service;

import static com.example.academy.type.MemberType.ROLE_ADMIN;

import com.example.academy.domain.Member;
import com.example.academy.domain.ProfileImage;
import com.example.academy.dto.LoginRequestDTO;
import com.example.academy.repository.ProfileImageRepository;
import com.example.academy.repository.UserRepository;
import com.example.academy.type.MemberType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

  private final UserRepository userRepository;
  private final ProfileImageRepository profileImageRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public JoinService(UserRepository userRepository, ProfileImageRepository profileImageRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.profileImageRepository = profileImageRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  public void joinProcess(LoginRequestDTO joinDTO) {

    String memberId = joinDTO.getMemberId();
    String password = joinDTO.getPassword();
    String name = joinDTO.getName();
    String email = joinDTO.getEmail();
    String phone = joinDTO.getPhone();
    MemberType memberType = joinDTO.getMemberType();

    boolean isExistMember = userRepository.existsByMemberId(memberId);
    boolean isExistEmail = userRepository.existsByEmail(email);
    boolean isExistPhone = userRepository.existsByPhone(phone);

    if (isExistMember || isExistEmail || isExistPhone) {
      String errorMessage = "";
      if (isExistMember) {
        errorMessage += "memberId 중복. ";
      }
      if (isExistEmail) {
        errorMessage += "email 중복. ";
      }
      if (isExistPhone) {
        errorMessage += "phone 중복. ";
      }
      throw new IllegalArgumentException(errorMessage.trim()); // 예외 던지기
    }

    Member data = new Member();

    data.setMemberId(memberId);
    data.setPassword(bCryptPasswordEncoder.encode(password));
    data.setName(name);
    data.setEmail(email);
    data.setPhone(phone);
    data.setMemberType(memberType);
    // ProfileImage 테이블의 총 레코드 수 가져오기
    long count = profileImageRepository.count();

// 1부터 count까지의 랜덤 ID 생성
    int randomId = (int) (Math.random() * count) + 1;

// 랜덤 ID에 해당하는 ProfileImage 가져오기
    ProfileImage profileImage = profileImageRepository.findById((long) randomId)
        .orElseThrow(() -> new RuntimeException("ProfileImage not found"));

// member 객체에 profileImage 설정
    data.setProfileImage(profileImage);

    userRepository.save(data);
  }
}










