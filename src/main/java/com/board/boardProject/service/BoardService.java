package com.board.boardProject.service;

import com.board.boardProject.entity.Board;
import com.board.boardProject.repository.BoardRepository;
import com.board.boardProject.vo.BoardVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<BoardVO> findBoardList() {
//        List<Board> boardList =  boardRepository.findAll();
        List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
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
        boardRepository.updateBoard(boardVO.getNo(), boardVO.getTitle(), boardVO.getContent());
    }

    public void saveBoard(BoardVO boardVO) {
        List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "no"));
        int maxNo = boardList.get(0).getNo();
        boardVO.setNo(maxNo + 1);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        boardVO.setCreatedAt(timestamp);

        Board board = modelMapper.map(boardVO, Board.class);
        boardRepository.save(board);
    }

    public void updateViewCount(BoardVO boardVO) {
        boardRepository.updateViewCount(boardVO.getNo(), (boardVO.getViewCount() + 1));

    }
}
