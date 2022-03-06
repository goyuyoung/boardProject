package com.board.boardProject.controller.api.board;

import com.board.boardProject.service.BoardService;
import com.board.boardProject.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
public class BoardWriteController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/saveBoard")
    public void saveBoard(BoardVO boardVO) {
        boardService.saveBoard(boardVO);
    }
}
