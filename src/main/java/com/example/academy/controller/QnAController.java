package com.example.academy.controller;

import com.example.academy.domain.Question;
import com.example.academy.repository.AnswerRepository;
import com.example.academy.repository.QuestionRepository;
import com.example.academy.repository.UserRepository;
import com.example.todo_crud.model.ToDo;
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
@RequestMapping("/qna")
public class QnAController {

  private AnswerRepository answerRepository;
  private QuestionRepository questionRepository;
  private UserRepository userRepository;

  @Autowired
  public QnAController(AnswerRepository answerRepository, QuestionRepository questionRepository,
      UserRepository userRepository) {
    this.answerRepository = answerRepository;
    this.questionRepository = questionRepository;
    this.userRepository = userRepository;
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
   * 질문 수정 (수강)
   * <p>
   * 질문 삭제 (수강)
   * <p>
   * 질문에 접근 (id) (강사님) *
   * <p>
   * 질문 수정 (강사)
   * <p>
   * 질문 삭제 (강사)
   * <p>
   * 질문 답변 작성 (강사)
   * <p>
   * 질문 답변 수정 (강사)
   * <p>
   * 질문 답변 삭제 (강사)
   * <p>
   * 질문 개추 (강사)
   * <p>
   * 질문 답변 완료 (강사)
   * <p>
   * 질문 CR*UD 답변 CRUD 질문 추천 질문 완료
   */

  @GetMapping
  public ResponseEntity<List<Question>> getAllQuestions() {
    List<Question> listQuestion = questionRepository.findAll();
    return ResponseEntity.ok(listQuestion);
  }

  @GetMapping
  public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
    Question question = questionRepository.findById(id).orElseThrow();
    return ResponseEntity.ok(question);
  }

  @PostMapping
  public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
    Question newQuestion = questionRepository.save(question);
    return ResponseEntity.status(HttpStatus.CREATED).body(newQuestion);
  }

  @PostMapping
  public ResponseEntity<ToDo> createToDo(@RequestBody ToDo toDo) {
    ToDo newToDo = toDoRepository.save(toDo);
    return ResponseEntity.status(HttpStatus.CREATED).body(newToDo);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ToDo> getToDoById(@PathVariable Long id) {
    ToDo toDo = toDoRepository.findById(id).orElseThrow();
    return ResponseEntity.ok(toDo);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ToDo> updateToDo(@PathVariable Long id, @RequestBody ToDo toDo) {
    ToDo existingToDo = toDoRepository.findById(id).orElseThrow();
    existingToDo.setTitle(toDo.getTitle());
    existingToDo.setContent(toDo.getContent());
    existingToDo.setCompleted(toDo.isCompleted());
    ToDo updatedToDo = toDoRepository.save(existingToDo);
    return ResponseEntity.ok(updatedToDo);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteToDo(@PathVariable Long id) {
    toDoRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}/complete")
  public ResponseEntity<ToDo> toggleComplete(@PathVariable Long id) {
    ToDo toDo = toDoRepository.findById(id).orElseThrow();
    toDo.setCompleted(!toDo.isCompleted());
    ToDo updatedToDo = toDoRepository.save(toDo);
    return ResponseEntity.ok(updatedToDo);
  }
}