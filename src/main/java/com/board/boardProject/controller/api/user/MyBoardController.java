package com.board.boardProject.controller.api.user;

import com.board.boardProject.service.UserService;
import com.board.boardProject.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class MyBoardController {

    @Autowired
    private UserService userService;

    @GetMapping("/myBoardList")
    public List<BoardVO> findMyBordList(String name) {
        return userService.findMyBordList(name);
    }
}
