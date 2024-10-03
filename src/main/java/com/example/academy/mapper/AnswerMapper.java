package com.example.academy.mapper;

import com.example.academy.domain.Answer;
import com.example.academy.domain.User;
import com.example.academy.dto.AnswerCreateDTO;
import com.example.academy.dto.AnswerReadDTO;
import com.example.academy.dto.AnswerUpdateDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnswerMapper {

  AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

  // Domain to DTO
  @Mapping(source = "user.id", target = "userId")
  AnswerCreateDTO answerToAnswerCreateDTO(Answer answer);

  @Mapping(source = "user.name", target = "userName")
  @Mapping(source = "answer.id", target = "id")
  AnswerReadDTO answerToAnswerReadDTO(Answer answer, User user);

  @Mapping(source = "answer.id", target = "id")
  AnswerUpdateDTO answerToAnswerUpdateDTO(Answer answer);

  // DTO do Domain
  @Mapping(target = "id", ignore = true)      // id는 자동 생성되므로 무시
  @Mapping(source = "user", target = "user")
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  Answer answerCreateDTOToAnswer(AnswerCreateDTO answerCreateDTO, User user);

  List<AnswerReadDTO> answersToAnswerReadDTO(List<Answer> answers);
}