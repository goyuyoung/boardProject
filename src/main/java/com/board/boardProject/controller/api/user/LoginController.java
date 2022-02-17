package com.board.boardProject.controller.api.user;

import com.board.boardProject.service.UserService;
import com.board.boardProject.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/loginUser")
    public int loginUser(UserVO userVO, HttpSession session) {

        return userService.loginUser(userVO, session);
    }
}
