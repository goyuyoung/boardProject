package com.board.boardProject.service;

import com.board.boardProject.entity.Reply;
import com.board.boardProject.repository.ReplyRepository;
import com.board.boardProject.vo.ReplyVO;
import com.board.boardProject.vo.UserVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private  UserService userService;

    private ModelMapper modelMapper = new ModelMapper();

    public void saveReply(ReplyVO replyVO) {
        String uuid = UUID.randomUUID().toString();
        replyVO.setUuid(uuid);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        replyVO.setCreatedAt(timestamp);

        Reply reply = modelMapper.map(replyVO, Reply.class);
        replyRepository.save(reply);
    }

    public List<ReplyVO> findReply(int bno) {
        List<ReplyVO> replyVOList = new ArrayList<>();
        List<Reply> replyList = replyRepository.findReplyByBnoOrderByCreatedAtDesc(bno);
        for (Reply reply : replyList) {
            ReplyVO replyVO = modelMapper.map(reply, ReplyVO.class);

            UserVO userVO = userService.findMyInfo(reply.getCreatedBy());
            replyVO.setWriter(userVO.getName());

            replyVOList.add(replyVO);
        }
        return  replyVOList;
    }
 }
