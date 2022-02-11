package com.board.boardProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @GetMapping("/header")
    public String header(){
        return "/layout/header";
    }

    @GetMapping("/footer")
    public String footer(){
        return "/layout/footer";
    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @GetMapping("/boardWrite")
    public String boardWrite(){
        return "/board/boardWrite";
    }

    @GetMapping("/boardList")
    public String boardList(){
        return "/board/boardList";
    }

    @GetMapping("/myInfo")
    public String myInfo(){
        return "myInfo";
    }
}
