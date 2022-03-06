package com.board.boardProject.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserViewController {

    @GetMapping("/")
    public String loginMain(HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "user/login";
        } else {
            return "/board/boardList";
        }
    }

    @GetMapping("login")
    public String login(){
        return "/user/login";
    }

    @GetMapping("/join")
    public String join(){
        return "/user/join";
    }

    @GetMapping("/myInfo")
    public String myInfo(){
        return "/user/myInfo";
    }

    @GetMapping("/myBoard")
    public String myBoard(){
        return "/user/myBoard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/user/login";
    }
}
