package com.board.boardProject.repository;

import com.board.boardProject.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, String> {

    List<Reply> findReplyByBnoOrderByCreatedAtDesc(int bno);
}
