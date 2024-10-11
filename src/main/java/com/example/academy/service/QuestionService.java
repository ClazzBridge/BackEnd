package com.example.academy.service;

import com.example.academy.dto.question.QuestionCreateDTO;
import com.example.academy.dto.question.QuestionReadDTO;
import com.example.academy.dto.question.QuestionToggleRecommendedDTO;
import com.example.academy.dto.question.QuestionToggleSolvedDTO;
import com.example.academy.dto.question.QuestionUpdateDTO;
import com.example.academy.exception.post.PostEmptyException;
import com.example.academy.exception.post.PostNotFoundException;
import com.example.academy.mapper.question.QuestionMapper;
import com.example.academy.repository.mysql.MemberRepository;
import com.example.academy.repository.mysql.QuestionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionService {

  private final QuestionRepository questionRepository;
  private final MemberRepository memberRepository;
  private final QuestionMapper questionMapper = QuestionMapper.INSTANCE;

  @Autowired
  public QuestionService(QuestionRepository questionRepository, MemberRepository memberRepository) {
    this.questionRepository = questionRepository;
    this.memberRepository = memberRepository;
  }

  public List<QuestionReadDTO> getAllQuestions() {
    return questionRepository.findAllQuestionReadDTOs();
  }

  public QuestionReadDTO getQuestionById(Long id) {
    Question question = questionRepository.findById(id).orElse(null);
    return questionMapper.questionToQuestionReadDTO(question, question.getMember());
  }

  public QuestionReadDTO createQuestion(QuestionCreateDTO questionCreateDTO) {
    Member member = memberRepository.findById(questionCreateDTO.getMemberId())
        .orElseThrow(() -> new RuntimeException("User not found"));
    Question newQuestion = questionMapper.questionCreateDTOToQuestion(questionCreateDTO, member);
    Question savedQuestion = questionRepository.save(newQuestion);

    return questionMapper.questionToQuestionReadDTO(savedQuestion, member);
  }

  public QuestionReadDTO updateQuestion(QuestionUpdateDTO questionUpdateDTO) {
    Long existingQuestionId = questionUpdateDTO.getId();
    Question existingQuestion = questionRepository.findById(existingQuestionId).orElseThrow();

    existingQuestion.updateContent(questionUpdateDTO.getContent());
    questionRepository.save(existingQuestion);

    return questionMapper.questionToQuestionReadDTO(existingQuestion, existingQuestion.getMember());
  }


  public QuestionReadDTO completeQuestion(QuestionToggleSolvedDTO questionToggleSolvedDTO) {
    Question existingQuestion = questionRepository.findById(questionToggleSolvedDTO.getId())
        .orElseThrow();

    existingQuestion.toggleSolved(questionToggleSolvedDTO.isSolved());
    Question updatedQuestion = questionRepository.save(existingQuestion);

    return questionMapper.questionToQuestionReadDTO(updatedQuestion, updatedQuestion.getMember());
  }

  public QuestionReadDTO recommendQuestion(
      QuestionToggleRecommendedDTO questionToggleRecommendedDTO) {
    Question existingQuestion = questionRepository.findById(questionToggleRecommendedDTO.getId())
        .orElseThrow();

    existingQuestion.toggleRecommended(questionToggleRecommendedDTO.isRecommended());
    questionRepository.save(existingQuestion);

    return questionMapper.questionToQuestionReadDTO(existingQuestion, existingQuestion.getMember());
  }

  public Page<QuestionReadDTO> getPageQuestions(Pageable pageable) {
    return questionRepository.findAllQuestionReadDTOs(pageable);  // 질문 목록을 페이지네이션으로 조회
  }

  public void deleteQuestion(List<Long> ids) {
    if (ids.isEmpty()) {
      throw new PostEmptyException();
    }

    for (Long id : ids) {
      Question deletedQuestion = questionRepository.findById(id)
          .orElseThrow(() -> new PostNotFoundException(id));
      questionRepository.delete(deletedQuestion);
    }
  }
}
