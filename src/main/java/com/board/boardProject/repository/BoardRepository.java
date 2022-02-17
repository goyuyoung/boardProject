package com.board.boardProject.repository;

import com.board.boardProject.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    List<Board> findByNoAndLockPw(int no, String lockPw);

    Board findByNo(int no);

    @Transactional
    @Modifying
    @Query(value=" UPDATE board "
                + " SET title = :title "
                + " , content = :content "
                + " , created_at = now() "
                + " WHERE no = :no ", nativeQuery = true)
    void updateBoard(@Param("no") int no, @Param("title") String title, @Param("content") String content);

    @Transactional
    @Modifying
    @Query(value=" UPDATE board "
            + " SET view_count = :viewCount"
            + " WHERE no = :no ", nativeQuery = true)
    void updateViewCount(@Param("no") int no, @Param("viewCount") int viewCount);
}
