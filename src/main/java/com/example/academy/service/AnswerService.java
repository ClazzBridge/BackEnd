package com.example.academy.service;

import com.example.academy.domain.Answer;
import com.example.academy.domain.Member;
import com.example.academy.domain.Question;
import com.example.academy.dto.AnswerCreateDTO;
import com.example.academy.dto.AnswerReadDTO;
import com.example.academy.dto.AnswerUpdateDTO;
import com.example.academy.mapper.AnswerMapper;
import com.example.academy.repository.AnswerRepository;
import com.example.academy.repository.MemberRepository;
import com.example.academy.repository.QuestionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

  private final AnswerRepository answerRepository;
  private final AnswerMapper answerMapper = AnswerMapper.INSTANCE;
  private final MemberRepository memberRepository;
  private final QuestionRepository questionRepository;

  @Autowired
  public AnswerService(AnswerRepository answerRepository, MemberRepository memberRepository,
      QuestionRepository questionRepository) {
    this.answerRepository = answerRepository;
    this.memberRepository = memberRepository;
    this.questionRepository = questionRepository;
  }

//  public List<AnswerReadDTO> getAllAnswer() {
//    return answerRepository.findAll();
//  }

  public AnswerReadDTO createAnswer(AnswerCreateDTO answerCreateDTO) {
    System.out.println(answerCreateDTO.getMemberId());
    System.out.println(answerCreateDTO.getQuestionId());

    Member member = memberRepository.findById(answerCreateDTO.getMemberId()).orElseThrow();
    Question question = questionRepository.findById(answerCreateDTO.getQuestionId()).orElseThrow();
    String content = answerCreateDTO.getContent();

    Answer newAnswer = answerMapper.answerCreateDTOToAnswer(answerCreateDTO, member);

    newAnswer.setMember(member);
    newAnswer.setQuestion(question);
    newAnswer.setContent(content);

    Answer createdAnswer = answerRepository.save(newAnswer);

    return answerMapper.answerToAnswerReadDTO(createdAnswer, member);
  }

  public AnswerReadDTO updateAnswer(AnswerUpdateDTO answerUpdateDTO) {
    Answer existingAnswer = answerRepository.findById(answerUpdateDTO.getId()).orElseThrow();
    Member member = existingAnswer.getMember();
    existingAnswer.setContent(answerUpdateDTO.getContent());
    Answer updatedAnswer = answerRepository.save(existingAnswer);

    return answerMapper.answerToAnswerReadDTO(updatedAnswer, member);
  }

  public void deleteAnswer(Long id) {
    answerRepository.deleteById(id);
  }

  public List<AnswerReadDTO> getAnswersByQuestionId(Long id) {
    List<AnswerReadDTO> answerReadDTOList = answerMapper.answersToAnswerReadDTO(
        answerRepository.findByQuestionId(id));

    for (AnswerReadDTO answerReadDTO : answerReadDTOList) {
      Answer existingAnswer = answerRepository.findById(answerReadDTO.getId()).orElseThrow();
      Member member = existingAnswer.getMember();
      answerReadDTO.setMemberName(member.getName());
    }

    return answerReadDTOList;
  }

  public Answer getAnswerById(Long id) {
    return answerRepository.findById(id).get();
  }
}
