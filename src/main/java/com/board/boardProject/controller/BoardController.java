package com.board.boardProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
