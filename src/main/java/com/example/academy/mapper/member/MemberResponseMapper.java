package com.example.academy.mapper.member;

import com.example.academy.domain.mysql.Member;
import com.example.academy.dto.auth.AuthResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class MemberResponseMapper {

    public static AuthResponseDTO ToDTO(Member member) {
        return AuthResponseDTO.builder()
            .id(member.getId())
            .email(member.getEmail())
            .name(member.getName())
            .memberType(member.getMemberType())
            .gitUrl(member.getGitUrl())
            .profileImageUrl(member.getProfileImage().getPictureUrl())
            .build();
    }

}
