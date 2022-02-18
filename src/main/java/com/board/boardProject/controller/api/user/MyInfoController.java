package com.board.boardProject.controller.api.user;

import com.board.boardProject.service.UserService;
import com.board.boardProject.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class MyInfoController {

    @Autowired
    private UserService userService;

    @GetMapping("/findMyInfo")
    public UserVO findMyInfo(UserVO userVO) {
        return userService.findMyInfo(userVO.getUuid());
    }

    @PostMapping("/updateMyInfo")
    public void updateMyInfo(UserVO userVO) {
        userService.updateMyInfo(userVO);
    }
}
