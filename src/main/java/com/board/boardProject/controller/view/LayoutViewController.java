package com.board.boardProject.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LayoutViewController {

    @GetMapping("/header")
    public String header(ModelMap model, HttpSession session){
        model.addAttribute("userId",session.getAttribute("userId"));
        model.addAttribute("userName",session.getAttribute("userName"));
        model.addAttribute("userUuid",session.getAttribute("userUuid"));
        return "/layout/header";
    }

    @GetMapping("/footer")
    public String footer(){
        return "/layout/footer";
    }

}
