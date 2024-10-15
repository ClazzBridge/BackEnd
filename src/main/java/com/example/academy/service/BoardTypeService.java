package com.example.academy.service;

import com.example.academy.domain.mysql.BoardType;
import com.example.academy.dto.boardType.BoardTypeSaveDTO;
import com.example.academy.repository.mysql.BoardTypeRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BoardTypeService {

    private final BoardTypeRepository boardTypeRepository;

    public BoardTypeService(BoardTypeRepository boardTypeRepository) {
        this.boardTypeRepository = boardTypeRepository;
    }

    public List<BoardType> getAllBoardTypes() {
        return boardTypeRepository.findAll();
    }

    public BoardType save(BoardTypeSaveDTO boardTypeSaveDTO) {
        BoardType boardType = new BoardType(boardTypeSaveDTO.getType());
        boardTypeRepository.save(boardType);
        return boardType;
    }

    public BoardType update() {
        return null;
    }

    public void delete(Long id) {
    }
}
