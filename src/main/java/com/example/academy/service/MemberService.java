package com.example.academy.service;

import com.example.academy.domain.Member;
import com.example.academy.repository.MemberRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

  private MemberRepository memberRepository;

  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public Member getMemberById(Long id) {
    return memberRepository.findById(id).orElseThrow();
  }

  public Optional<Member> findByEmail(String email) {
    return memberRepository.findByEmail(email);
  }
}
