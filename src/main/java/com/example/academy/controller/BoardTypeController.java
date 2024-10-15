package com.example.academy.controller;

import com.example.academy.domain.mysql.BoardType;
import com.example.academy.dto.boardType.BoardTypeSaveDTO;
import com.example.academy.service.BoardTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/boardTypes/")
public class BoardTypeController {

    private final BoardTypeService boardTypeService;

    public BoardTypeController(BoardTypeService boardTypeService) {
        this.boardTypeService = boardTypeService;
    }

    @Operation(summary = "게시판 타입 리스트 반환", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("")
    public ResponseEntity<List<BoardType>> getAllBoardTypes() {
        List<BoardType> boardTypes = boardTypeService.getAllBoardTypes();
        return ResponseEntity.ok().body(boardTypes);
    }

    @Operation(summary = "게시판 타입 추가", security = {@SecurityRequirement(name = "bearerAuth")})
    @PostMapping("")
    public ResponseEntity<BoardType> save(BoardTypeSaveDTO boardTypeSaveDTO) {
        BoardType boardTypes = boardTypeService.save(boardTypeSaveDTO);
        return ResponseEntity.ok().body(boardTypes);
    }

    @Operation(summary = "게시판 타입 수정", security = {@SecurityRequirement(name = "bearerAuth")})
    @PutMapping("")
    public ResponseEntity<BoardType> update() {
        BoardType boardTypes = boardTypeService.update();
        return ResponseEntity.ok().body(boardTypes);
    }

    @Operation(summary = "게시판 타입 제거", security = {@SecurityRequirement(name = "bearerAuth")})
    @DeleteMapping("")
    public HttpStatus delete(@RequestBody Long id) {
        boardTypeService.delete(id);
        return HttpStatus.OK;
    }

}
