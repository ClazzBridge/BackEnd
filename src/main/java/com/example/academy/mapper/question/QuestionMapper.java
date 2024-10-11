package com.example.academy.mapper.question;

import com.example.academy.dto.question.QuestionCreateDTO;
import com.example.academy.dto.question.QuestionReadDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionMapper {

  QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

  // Domain to DTO
  @Mapping(source = "member.name", target = "memberName")
  @Mapping(source = "question.id", target = "id")
  @Mapping(source = "question.solved", target = "isSolved")
  @Mapping(source = "question.recommended", target = "isRecommended")
  QuestionReadDTO questionToQuestionReadDTO(Question question, Member member);


  // DTO do Domain
  @Mapping(target = "id", ignore = true)      // id는 자동 생성되므로 무시
  @Mapping(target = "solved", ignore = true)
  @Mapping(target = "recommended", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(source = "member", target = "member")
  Question questionCreateDTOToQuestion(QuestionCreateDTO questionCreateDTO, Member member);
}
