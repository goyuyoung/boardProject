package com.board.boardProject.service;

import com.board.boardProject.entity.User;
import com.board.boardProject.repository.BoardRepository;
import com.board.boardProject.repository.UserRepository;
import com.board.boardProject.vo.UserVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public int checkId(UserVO userVO) {
        List<User> userList = userRepository.findByUserId(userVO.getUserId());
        int cnt = userList.size();
        return cnt;

    }

    public void joinUser(UserVO userVO) {
        String uuid = UUID.randomUUID().toString();
        userVO.setUuid(uuid);
        User user = modelMapper.map(userVO, User.class);
        userRepository.save(user);
    }

    public int loginUser(UserVO userVO, HttpSession session) {
        List<User> boardList = userRepository.findByUserIdAndUserPassword(userVO.getUserId(), userVO.getUserPassword());
        int listLength = boardList.size();
        if(listLength > 0) {
            //로그인 가능
            session.setAttribute("userId", userVO.getUserId());
            session.setAttribute("userName", boardList.get(0).getName());
            session.setAttribute("userUuid", boardList.get(0).getUuid());
        }
        return  boardList.size();
    }

    public UserVO findMyInfo(String uuid) {
        List<User> userList = userRepository.findByUuid(uuid);;
        List<UserVO> userVOList = new ArrayList<>();
        for(User user : userList) {
            UserVO userVO = modelMapper.map(user,UserVO.class);
            userVOList.add(userVO);
        }
        return userVOList.get(0);
    }

    public void updateMyInfo(UserVO userVO) {
        User user = modelMapper.map(userVO, User.class);
        userRepository.save(user);
    }
 }
