package com.example.academy.controller;

import com.example.academy.domain.Question;
import com.example.academy.dto.QuestionCreateDTO;
import com.example.academy.dto.QuestionUpdateDTO;
import com.example.academy.service.QestionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

  private QestionService questionService;

  @Autowired
  public QuestionController(QestionService questionService) {
    this.questionService = questionService;
  }

  //요청이 들어왔을 때 처리해야 한다
  // 기능

  /**
   * 게시판 접근 - 목록을 리턴 *
   * <p>
   * 질문 생성 (수강) *
   * <p>
   * 질문에 접근 (id) (수강) * - 질문 데이터를 리턴
   * <p>
   * 질문 수정 (수강) *
   * <p>
   * 질문 삭제 (수강) *
   * <p>
   * 질문에 접근 (id) (강사님) *
   * <p>
   * 질문 수정 (강사) *
   * <p>
   * 질문 삭제 (강사) *
   * <p>
   * 질문 개추 (강사) *
   * <p>
   * 질문 답변 완료 (강사) *
   * <p>
   */

  //Question CRUD
  @GetMapping("/")
  public ResponseEntity<List<Question>> getAllQuestions() {
    List<Question> listQuestion = questionService.getAllQuestions();
    return ResponseEntity.ok(listQuestion);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
    Question question = questionService.getQuestionById(id);
    return ResponseEntity.ok(question);
  }

  @PostMapping("/")
  public ResponseEntity<Question> createQuestion(@RequestBody QuestionCreateDTO createQuestionDTO) {
    Question newQuestion = questionService.createQuestion(createQuestionDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(newQuestion);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Question> updateQuestion(@PathVariable Long id,
      @RequestBody QuestionUpdateDTO questionUpdateDTO) {
    Question updateQuestion = questionService.updateQuestion(id, questionUpdateDTO);
    return ResponseEntity.ok(updateQuestion);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
    questionService.deleteQuestion(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}/complete")
  public ResponseEntity<Question> toggleQuestionComplete(@PathVariable Long id) {
    Question question = questionService.completeQuestion(id);
    return ResponseEntity.ok(question);
  }

  @PutMapping("/{id}/recommend")
  public ResponseEntity<Question> toggleQuestionRecommend(@PathVariable Long id) {
    Question question = questionService.recommendQuestion(id);
    return ResponseEntity.ok(question);
  }

}