package com.board.boardProject.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardViewController {

    @GetMapping("/boardWrite")
    public String boardWrite(){
        return "/board/boardWrite";
    }

    @GetMapping("/boardList")
    public String boardList(){
        return "/board/boardList";
    }

    @GetMapping("/boardDetail")
    public String boardDetail(){
        return "/board/boardDetail";
    }

    @GetMapping("/boardLock")
    public String boardLock(){
        return "/board/boardLock";
    }
}
