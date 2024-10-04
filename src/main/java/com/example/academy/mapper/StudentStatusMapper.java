package com.example.academy.mapper;

import com.example.academy.domain.StudentStatus;
import com.example.academy.dto.StudentStatusReadDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentStatusMapper {

  StudentStatusMapper INSTANCE = Mappers.getMapper(StudentStatusMapper.class);

  StudentStatusReadDTO studentStatusToStudentStatusReadDTO(StudentStatus studentStatus);
}
