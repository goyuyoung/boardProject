package com.board.boardProject.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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
    public String boardDetail(@RequestParam("no") int no, Model model){
        model.addAttribute("no",no);
        return "/board/boardDetail";
    }

    @GetMapping("/boardLock")
    public String boardLock(@RequestParam("no") int no, Model model){
        model.addAttribute("no",no);
        return "/board/boardLock";
    }
}
