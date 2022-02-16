package com.board.boardProject.controller.api.board;

import com.board.boardProject.service.BoardService;
import com.board.boardProject.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
public class BoardDetailController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/findBoardDetail")
    public BoardVO findBoardList(BoardVO boardVO) {
        return boardService.findBoardDetail(boardVO);
    }

    @GetMapping("/updateBoard")
    public void updateBoard(BoardVO boardVO) {
        boardService.updateBoard(boardVO);
    }


}
