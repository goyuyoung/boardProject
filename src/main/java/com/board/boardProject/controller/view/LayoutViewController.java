package com.board.boardProject.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LayoutViewController {

    @GetMapping("/header")
    public String header(){
        return "/layout/header";
    }

    @GetMapping("/footer")
    public String footer(){
        return "/layout/footer";
    }

}
