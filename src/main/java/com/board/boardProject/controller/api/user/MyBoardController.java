package com.board.boardProject.controller.api.user;

import com.board.boardProject.service.BoardService;
import com.board.boardProject.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class MyBoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/myBoardList")
    public PageVO findMyBordList(Pageable pageable, String uuid) {
        return boardService.findMyBordList(pageable, uuid);
    }
}
