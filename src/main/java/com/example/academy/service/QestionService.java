package com.example.academy.service;

import com.example.academy.domain.Question;
import com.example.academy.dto.QuestionCreateDTO;
import com.example.academy.dto.QuestionUpdateDTO;
import com.example.academy.repository.QuestionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QestionService {

  private QuestionRepository questionRepository;

  @Autowired
  public QestionService(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  public List<Question> getAllQuestions() {
    return questionRepository.findAll();
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
    existingQuestion.setAnswerComplete(!existingQuestion.isAnswerComplete());

    return questionRepository.save(existingQuestion);
  }

  public Question recommendQuestion(Long id) {
    Question existingQuestion = questionRepository.findById(id).orElseThrow();
    existingQuestion.setRecommend(!existingQuestion.isRecommend());

    return questionRepository.save(existingQuestion);
  }

}
