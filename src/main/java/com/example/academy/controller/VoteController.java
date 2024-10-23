package com.example.academy.controller;


import com.example.academy.domain.Vote;
import com.example.academy.dto.vote.AddVoteDTO;
import com.example.academy.dto.vote.GetVoteDTO;
import com.example.academy.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import java.util.Optional;
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
@RequestMapping("/api/vote")
public class VoteController {

  @Autowired
  private final VoteService voteService;

  public VoteController(VoteService voteService) {
    this.voteService = voteService;
  }

  @Operation(summary = "투표 전체 조회", security = {@SecurityRequirement(name = "bearerAuth")})
  @GetMapping
  public ResponseEntity<List<?>> getAllVote() {
    List<GetVoteDTO> name = voteService.getAllVote();
    return ResponseEntity.ok(name);
  }

  @GetMapping("{id}")
  @Operation(summary = "투표 조회", security = {@SecurityRequirement(name = "bearerAuth")})
  public ResponseEntity<?> getVote(@PathVariable Long id) {
    List<GetVoteDTO> title = voteService.getVote(id);
    if (title.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.status(HttpStatus.OK).body(title);
  }

  @PostMapping
  @Operation(summary = "투표 추가", security = {@SecurityRequirement(name = "bearerAuth")})
  public ResponseEntity<String> addVote(@RequestBody AddVoteDTO addVoteDTO) {
    try {
      voteService.addVote(addVoteDTO);
      return ResponseEntity.ok("추가완료");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("{id}")
  @Operation(summary = "투표 삭제", security = {@SecurityRequirement(name = "bearerAuth")})
  public ResponseEntity<?> deleteVote(@PathVariable Long id) {
    try {
      voteService.deleteVote(id);
      return ResponseEntity.ok("삭제 성공");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

//  @PutMapping
//  @Operation(summary = "투표 변경", security = {@SecurityRequirement(name = "bearerAuth")})
//  public ResponseEntity<?> updateVote(@RequestBody UpdateVoteDTO updateVoteDTO){
//    try {
//      voteService.updateVote(updateVoteDTO);
//    } catch (Exception e) {
//      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당하는 투표가 없습니다.");
//    }
//    return ResponseEntity.ok(updateVoteDTO);
//  }

}
