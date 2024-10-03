package com.example.academy.service;

import com.example.academy.domain.Question;
import com.example.academy.domain.User;
import com.example.academy.dto.QuestionCreateDTO;
import com.example.academy.dto.QuestionReadDTO;
import com.example.academy.dto.QuestionToggleRecommendedDTO;
import com.example.academy.dto.QuestionToggleSolvedDTO;
import com.example.academy.dto.QuestionUpdateDTO;
import com.example.academy.mapper.QuestionMapper;
import com.example.academy.repository.QuestionRepository;
import com.example.academy.repository.UserRepository;
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
  private final UserRepository userRepository;
  private final QuestionMapper questionMapper = QuestionMapper.INSTANCE;

  @Autowired
  public QuestionService(QuestionRepository questionRepository, UserRepository userRepository) {
    this.questionRepository = questionRepository;
    this.userRepository = userRepository;
  }

  public List<QuestionReadDTO> getAllQuestions() {
    return questionRepository.findAllQuestionReadDTOs();
  }

  public QuestionReadDTO getQuestionById(Long id) {
    Question question = questionRepository.findById(id).orElse(null);
    return questionMapper.questionToQuestionReadDTO(question, question.getUser());
  }

  public QuestionReadDTO createQuestion(QuestionCreateDTO questionCreateDTO) {
    User user = userRepository.findById(questionCreateDTO.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found"));
    Question newQuestion = questionMapper.questionCreateDTOToQuestion(questionCreateDTO, user);
    Question savedQuestion = questionRepository.save(newQuestion);

    return questionMapper.questionToQuestionReadDTO(savedQuestion, user);
  }

  public QuestionReadDTO updateQuestion(QuestionUpdateDTO questionUpdateDTO) {
    Long existingQuestionId = questionUpdateDTO.getId();
    Question existingQuestion = questionRepository.findById(existingQuestionId).orElseThrow();

    existingQuestion.updateContent(questionUpdateDTO.getContent());
    questionRepository.save(existingQuestion);

    return questionMapper.questionToQuestionReadDTO(existingQuestion, existingQuestion.getUser());
  }

  public void deleteQuestion(Long id) {
    questionRepository.deleteById(id);
  }

  public QuestionReadDTO completeQuestion(QuestionToggleSolvedDTO questionToggleSolvedDTO) {
    Question existingQuestion = questionRepository.findById(questionToggleSolvedDTO.getId())
        .orElseThrow();

    existingQuestion.toggleSolved(questionToggleSolvedDTO.isSolved());
    Question updatedQuestion = questionRepository.save(existingQuestion);

    return questionMapper.questionToQuestionReadDTO(updatedQuestion, updatedQuestion.getUser());
  }

  public QuestionReadDTO recommendQuestion(
      QuestionToggleRecommendedDTO questionToggleRecommendedDTO) {
    Question existingQuestion = questionRepository.findById(questionToggleRecommendedDTO.getId())
        .orElseThrow();

    existingQuestion.toggleRecommended(questionToggleRecommendedDTO.isRecommended());
    questionRepository.save(existingQuestion);

    return questionMapper.questionToQuestionReadDTO(existingQuestion, existingQuestion.getUser());
  }

  public Page<QuestionReadDTO> getPageQuestions(Pageable pageable) {
    return questionRepository.findAllQuestionReadDTOs(pageable);  // 질문 목록을 페이지네이션으로 조회
  }
}
