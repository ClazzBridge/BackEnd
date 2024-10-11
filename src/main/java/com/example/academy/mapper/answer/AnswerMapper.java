package com.example.academy.mapper.answer;

import com.example.academy.dto.answer.AnswerCreateDTO;
import com.example.academy.dto.answer.AnswerReadDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnswerMapper {

  AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

  // Domain to DTO
  @Mapping(source = "member.name", target = "memberName")
  @Mapping(source = "answer.id", target = "id")
  AnswerReadDTO answersToAnswerReadDTOs(Answer answer, Member member);

  // DTO do Domain
  @Mapping(target = "id", ignore = true)      // id는 자동 생성되므로 무시
  @Mapping(source = "member", target = "member")
  @Mapping(source = "question", target = "question")
  @Mapping(source = "answerCreateDTO.content", target = "content")
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  Answer answerCreateDTOToAnswer(AnswerCreateDTO answerCreateDTO, Member member, Question question);

  @Mapping(source = "answer.member.name", target = "memberName")
  AnswerReadDTO answerToAnswerReadDTO(Answer answer);

  List<AnswerReadDTO> answersToAnswerReadDTOs(List<Answer> answers);
}
