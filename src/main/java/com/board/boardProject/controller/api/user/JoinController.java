package com.board.boardProject.controller.api.user;

import com.board.boardProject.service.UserService;
import com.board.boardProject.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class JoinController {

    @Autowired
    private UserService userService;

    @GetMapping("/checkId")
    public int checkId(UserVO userVO) {
        return userService.checkId(userVO);
    }

    @PostMapping("/joinUser")
    public void joinUser(UserVO userVO) {
        userService.joinUser(userVO);
    }
}
