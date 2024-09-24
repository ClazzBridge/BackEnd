package com.example.academy.service;

import com.example.academy.domain.Answer;
import com.example.academy.dto.AnswerCreateDTO;
import com.example.academy.dto.AnswerUpdateDTO;
import com.example.academy.repository.AnswerRepository;
import com.example.academy.repository.QuestionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

  private AnswerRepository answerRepository;
  private QuestionRepository questionRepository;

  @Autowired
  public AnswerService(AnswerRepository answerRepository) {
    this.answerRepository = answerRepository;
  }

  public List<Answer> getAllAnswer() {
    return answerRepository.findAll();
  }

  public Answer createAnswer(AnswerCreateDTO answerCreateDTO) {
    String content = answerCreateDTO.getContent();

    Answer answer = new Answer();
    answer.setContent(content);

    return answerRepository.save(answer);
  }

  public Answer updateAnswer(Long id, AnswerUpdateDTO answerUpdateDTO) {
    Answer existingAnswer = answerRepository.findById(id).orElseThrow();
    existingAnswer.setContent(answerUpdateDTO.getContent());

    return answerRepository.save(existingAnswer);
  }

  public void deleteAnswer(Long id) {
    answerRepository.deleteById(id);
  }

  public List<Answer> getAnswersByQuestionId(Long id) {
    return answerRepository.findByQuestionId(id);
  }

  public Answer getAnswerById(Long id) {
    return answerRepository.findById(id).get();
  }
}
