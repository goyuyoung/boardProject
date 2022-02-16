package com.board.boardProject.service;

import com.board.boardProject.entity.Board;
import com.board.boardProject.repository.BoardRepository;
import com.board.boardProject.vo.BoardVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<BoardVO> findBoardList() {
//        List<Board> boardList =  boardRepository.findAll();
        List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "no"));
        List<BoardVO> boardVOList = new ArrayList<>();
        // EntityList -> VOList
        for(Board board : boardList) {
            BoardVO boardVO = modelMapper.map(board,BoardVO.class);
            boardVOList.add(boardVO);
        }
        return boardVOList;
    }

    public int checkLockPw(BoardVO boardVO) {
        int cnt = 0;
        List<Board> checkList = boardRepository.findByNoAndLockPw(boardVO.getNo(), boardVO.getLockPw());
        cnt = checkList.size();
        return cnt;
    }

    public BoardVO findBoardDetail(BoardVO boardVO) {
        Board board = boardRepository.findByNo(boardVO.getNo());
        BoardVO result = modelMapper.map(board, BoardVO.class);
        return result;
    }

    public void updateBoard(BoardVO boardVO) {
        Board board = modelMapper.map(boardVO, Board.class);
        boardRepository.save(board);
    }
}
