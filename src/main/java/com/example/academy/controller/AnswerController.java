package com.example.academy.controller;

import com.example.academy.domain.Answer;
import com.example.academy.dto.AnswerCreateDTO;
import com.example.academy.dto.AnswerUpdateDTO;
import com.example.academy.service.AnswerService;
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
@RequestMapping("/api/answers")
public class AnswerController {

  private AnswerService answerService;

  @Autowired
  public AnswerController(AnswerService answerService) {
    this.answerService = answerService;
  }

  //요청이 들어왔을 때 처리해야 한다
  // 기능

  /**
   * 질문 답변 작성 (강사) *
   * <p>
   * 질문 답변 수정 (강사) *
   * <p>
   * 질문 답변 전체 조회
   * <p>
   * 질문 답변 삭제 (강사) *
   * <p>
   * 질문 개추 (강사)
   * <p>
   * 질문 답변 완료 (강사)
   * <p>
   * 질문 CR*UD 답변 CRUD 질문 추천 질문 완료
   */

  // Answer CRUD
  @GetMapping("")
  public ResponseEntity<List<Answer>> getAllAnswer() {
    List<Answer> listAnswer = answerService.getAllAnswer();
    return ResponseEntity.ok(listAnswer);
  }

  // 특정 질문의 답변 목록을 반환하는 API
  @GetMapping("/{id}")
  public ResponseEntity<List<Answer>> getAnswersByQuestionId(@PathVariable Long id) {
    List<Answer> answers = answerService.getAnswersByQuestionId(id);
    return ResponseEntity.ok(answers);  // 답변 목록을 응답으로 반환
  }

  @PostMapping("")
  public ResponseEntity<Answer> createAnswer(@RequestBody AnswerCreateDTO answerCreateDTO) {
    Answer newAnswer = answerService.createAnswer(answerCreateDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(newAnswer);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Answer> updateQuestion(@PathVariable Long id,
      @RequestBody AnswerUpdateDTO answerUpdateDTO) {
    Answer updateAnswer = answerService.updateAnswer(id, answerUpdateDTO);
    return ResponseEntity.ok(updateAnswer);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
    answerService.deleteAnswer(id);
    return ResponseEntity.noContent().build();
  }
//
//  @PutMapping("/{id}/complete")
//  public ResponseEntity<ToDo> toggleComplete(@PathVariable Long id) {
//    ToDo toDo = toDoRepository.findById(id).orElseThrow();
//    toDo.setCompleted(!toDo.isCompleted());
//    ToDo updatedToDo = toDoRepository.save(toDo);
//    return ResponseEntity.ok(updatedToDo);
//  }
}