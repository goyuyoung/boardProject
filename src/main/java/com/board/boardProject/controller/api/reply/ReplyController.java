package com.board.boardProject.controller.api.reply;

import com.board.boardProject.service.ReplyService;
import com.board.boardProject.vo.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping("/saveReply")
    public void saveReply(ReplyVO ReplyVO) {
        replyService.saveReply(ReplyVO);
    }

    @GetMapping("/findReply")
    public List<ReplyVO> findReply(int bno) {
        return replyService.findReply(bno);
    }

}
