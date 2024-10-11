package com.example.academy.service;


import com.example.academy.domain.mysql.Member;
import com.example.academy.repository.mysql.MemberRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public Optional<Member> getUserProfileById(Long id) {
    return memberRepository.findById(id);
  }

  public Member createOrUpdateUserProfile(Member member) {
    // 비밀번호가 null이 아니고 변경된 경우에만 암호화
    if (member.getPassword() != null && !member.getPassword().isEmpty()) {
      // 비밀번호 암호화
      String encodedPassword = passwordEncoder.encode(member.getPassword());
      member.setPassword(encodedPassword);
    }
    return memberRepository.save(member);
  }

  // 비밀번호를 확인하는 메서드
  public boolean checkPassword(Long userId, String inputPassword) {
    Optional<Member> userOptional = getUserProfileById(userId);

    if (userOptional.isPresent()) {
      Member user = userOptional.get();

      return passwordEncoder.matches(inputPassword, user.getPassword());
    }

    return false; // 사용자가 없을 경우 false 반환
  }
}