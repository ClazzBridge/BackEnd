package com.example.academy.mapper;

import com.example.academy.domain.StudentStatus;
import com.example.academy.dto.StudentStatusReadDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentStatusMapper {

  StudentStatusMapper INSTANCE = Mappers.getMapper(StudentStatusMapper.class);

  @Mapping(source = "member.id", target = "memberId")
  @Mapping(source = "handRaised", target = "isHandRaised")
  @Mapping(source = "understanding", target = "isUnderstanding")
  StudentStatusReadDTO studentStatusToStudentStatusReadDTO(StudentStatus studentStatus);
}
