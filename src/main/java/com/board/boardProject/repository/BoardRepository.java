package com.board.boardProject.repository;

import com.board.boardProject.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    List<Board> findByNoAndLockPw(int no, String lockPw);

    Board findByNo(int no);
}
