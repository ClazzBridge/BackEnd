package com.example.academy.service;

import com.example.academy.controller.QnAController;
import com.example.academy.domain.Question;
import com.example.academy.dto.CreateQuestionDTO;
import com.example.academy.repository.AnswerRepository;
import com.example.academy.repository.QuestionRepository;
import com.example.academy.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class QnAService {

  private AnswerRepository answerRepository;
  private QuestionRepository questionRepository;
  private UserRepository userRepository;

  @Autowired
  public QnAService(AnswerRepository answerRepository, QuestionRepository questionRepository,
      UserRepository userRepository) {
    this.answerRepository = answerRepository;
    this.questionRepository = questionRepository;
    this.userRepository = userRepository;
  }

  public List<Question> getAllQuestions() {
    return questionRepository.findAll();
  }

  public Question getQuestionById(@PathVariable Long id) {
    return questionRepository.findById(id).orElseThrow();
  }

  public Question createQuestion(CreateQuestionDTO createQuestionDTO) {
    String content = createQuestionDTO.getContent();
    Long id = createQuestionDTO.getId();

    Question question = new Question();
    question.setContent(content);

    return QnAController.createQuestion(question);
  }

  public void deleteQuestion(Long id) {
    if (!questionRepository.existsById(id)) {
    }
  }


}
