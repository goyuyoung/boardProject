package com.board.boardProject.controller.api.board;

import com.board.boardProject.service.BoardService;
import com.board.boardProject.vo.BoardVO;
import com.board.boardProject.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardListController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/boardList")
    public PageVO findBoardList(Pageable pageable) {
        return boardService.findBoardList(pageable);
    }

    @PostMapping("/updateViewCount")
    public void updateViewCount(BoardVO boardVO) {
        boardService.updateViewCount(boardVO);
    }
}
