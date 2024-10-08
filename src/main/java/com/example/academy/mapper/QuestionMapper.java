package com.example.academy.mapper;

import com.example.academy.domain.Member;
import com.example.academy.domain.Question;
import com.example.academy.dto.QuestionCreateDTO;
import com.example.academy.dto.QuestionReadDTO;
import com.example.academy.dto.QuestionUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionMapper {

  QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

  // Domain to DTO
  @Mapping(source = "member.id", target = "memberId")
  QuestionCreateDTO questionToQuestionCreateDTO(Question question);

  @Mapping(source = "member.name", target = "memberName")
  @Mapping(source = "question.id", target = "id")
  @Mapping(source = "question.solved", target = "isSolved")
  @Mapping(source = "question.recommended", target = "isRecommended")
  QuestionReadDTO questionToQuestionReadDTO(Question question, Member member);

  @Mapping(source = "question.id", target = "id")
  QuestionUpdateDTO questionToQuestionUpdateDTO(Question question);

  // DTO do Domain
  @Mapping(target = "id", ignore = true)      // id는 자동 생성되므로 무시
  @Mapping(target = "solved", ignore = true)
  @Mapping(target = "recommended", ignore = true)
  @Mapping(target = "createDate", ignore = true)
  @Mapping(source = "member", target = "member")
  Question questionCreateDTOToQuestion(QuestionCreateDTO questionCreateDTO, Member member);
}
