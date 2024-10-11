package com.example.academy.controller;

import com.example.academy.dto.question.QuestionCreateDTO;
import com.example.academy.dto.question.QuestionReadDTO;
import com.example.academy.dto.question.QuestionToggleRecommendedDTO;
import com.example.academy.dto.question.QuestionToggleSolvedDTO;
import com.example.academy.dto.question.QuestionUpdateDTO;
import com.example.academy.service.QuestionService;
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
@RequestMapping("/api/questions")
public class QuestionController {

  private QuestionService questionService;

  @Autowired
  public QuestionController(QuestionService questionService) {
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
//
//  //Question CRUD
//  @GetMapping
//  public ResponseEntity<Map<String, Object>> getQuestionsByPage(@RequestParam("page") int page) {
//    Pageable pageable = PageRequest.of(page - 1, 10);  // 페이지 당 10개의 질문을 가져오기 위해 Pageable 설정
//
//    Page<QuestionReadDTO> questionsPage = questionService.getPageQuestions(pageable);
//
//    // 질문 목록과 총 페이지 수를 담는 응답 데이터를 구성
//    Map<String, Object> response = new HashMap<>();
//    response.put("questions", questionsPage.getContent());  // 현재 페이지의 질문 목록
//    response.put("totalPages", questionsPage.getTotalPages());  // 총 페이지 수
//
//    return ResponseEntity.ok(response);  // 응답을 반환
//  }

  @GetMapping
  public ResponseEntity<List<QuestionReadDTO>> getAllQuestions() {
    List<QuestionReadDTO> questions = questionService.getAllQuestions();
    return ResponseEntity.ok(questions);
  }

  @GetMapping("/{id}")
  public ResponseEntity<QuestionReadDTO> getQuestionById(@PathVariable("id") Long id) {
    QuestionReadDTO questionReadDTO = questionService.getQuestionById(id);
    return ResponseEntity.ok(questionReadDTO);
  }

  @PostMapping
  public ResponseEntity<QuestionReadDTO> createQuestion(
      @RequestBody QuestionCreateDTO createQuestionDTO) {
    QuestionReadDTO newQuestionReadDTO = questionService.createQuestion(createQuestionDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(newQuestionReadDTO);
  }

  @PutMapping()
  public ResponseEntity<QuestionReadDTO> updateQuestion(
      @RequestBody QuestionUpdateDTO questionUpdateDTO) {
    QuestionReadDTO updatedQuestionReadDTO = questionService.updateQuestion(questionUpdateDTO);
    return ResponseEntity.status(HttpStatus.OK).body(updatedQuestionReadDTO);
  }

  @DeleteMapping("/{ids}")
  public ResponseEntity<?> deleteQuestion(@PathVariable("ids") List<Long> ids) {
    questionService.deleteQuestion(ids);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/solved")
  public ResponseEntity<QuestionReadDTO> toggleQuestionComplete(
      @RequestBody QuestionToggleSolvedDTO questionToggleSolvedDTO) {
    QuestionReadDTO updatedQuestionReadDTO = questionService.completeQuestion(
        questionToggleSolvedDTO);
    return ResponseEntity.status(HttpStatus.OK).body(updatedQuestionReadDTO);
  }

  @PutMapping("/recommended")
  public ResponseEntity<QuestionReadDTO> toggleQuestionRecommend(
      @RequestBody QuestionToggleRecommendedDTO questionToggleRecommendedDTO) {
    QuestionReadDTO updatedQuestionReadDTO = questionService.recommendQuestion(
        questionToggleRecommendedDTO);
    return ResponseEntity.status(HttpStatus.OK).body(updatedQuestionReadDTO);
  }

}