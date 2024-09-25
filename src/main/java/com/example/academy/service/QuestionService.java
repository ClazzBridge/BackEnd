package com.example.academy.service;

import com.example.academy.domain.Question;
import com.example.academy.dto.QuestionCreateDTO;
import com.example.academy.dto.QuestionReadDTO;
import com.example.academy.dto.QuestionUpdateDTO;
import com.example.academy.repository.QuestionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

  private QuestionRepository questionRepository;

  @Autowired
  public QuestionService(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  public List<QuestionReadDTO> getAllQuestions() {
    return questionRepository.findAllQuestionReadDTOs();
  }

  public Question getQuestionById(Long id) {
    return questionRepository.findById(id).orElseThrow();
  }

  public Question createQuestion(QuestionCreateDTO questionCreateDTO) {
    String content = questionCreateDTO.getContent();

    Question question = new Question();
    question.setContent(content);

    return questionRepository.save(question);
  }

  public Question updateQuestion(Long id, QuestionUpdateDTO questionUpdateDTO) {
    Question existingQuestion = questionRepository.findById(id).orElseThrow();
    existingQuestion.setContent(questionUpdateDTO.getContent());

    return questionRepository.save(existingQuestion);
  }

  public void deleteQuestion(Long id) {
    questionRepository.deleteById(id);
  }

  public Question completeQuestion(Long id) {
    Question existingQuestion = questionRepository.findById(id).orElseThrow();
    existingQuestion.setSolved(!existingQuestion.isSolved());

    return questionRepository.save(existingQuestion);
  }

  public Question recommendQuestion(Long id) {
    Question existingQuestion = questionRepository.findById(id).orElseThrow();
    existingQuestion.setRecommended(!existingQuestion.isRecommended());

    return questionRepository.save(existingQuestion);
  }

  public Page<QuestionReadDTO> getPageQuestions(Pageable pageable) {
    return questionRepository.findAllQuestionReadDTOs(pageable);  // 질문 목록을 페이지네이션으로 조회
  }
}
