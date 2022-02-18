package com.board.boardProject.service;

import com.board.boardProject.entity.Board;
import com.board.boardProject.repository.BoardRepository;
import com.board.boardProject.vo.BoardVO;
import com.board.boardProject.vo.PageVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public PageVO findBoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(), Sort.by("createdAt").descending());
        Page<Board> pageBoardList = boardRepository.findAll(pageable);
        return setPageParam(pageable, pageBoardList);
    }
    public PageVO setPageParam(Pageable pageable, Page<Board> list) {
        int pageSize = pageable.getPageSize();
        int totalPageSize = (int)list.getTotalElements();
        int currentPage = pageable.getPageNumber() + 1;
        int endPage =  ((pageable.getPageNumber() / 5) + 1) * 5;
        if (endPage * pageSize > totalPageSize) {
            endPage = totalPageSize / pageSize + 1;
            if (totalPageSize % pageSize == 0) {
                endPage -= 1;
            }
        }
        int startPage = (pageable.getPageNumber() / 5 * 5 == 0 ? 1 : pageable.getPageNumber() / 5 * 5 + 1);
        boolean prev = startPage == 1 ? false : true;
        boolean next = endPage * pageSize >= totalPageSize ? false : true;

        PageVO pageVO = new PageVO();
        pageVO.setList(list.getContent());
        pageVO.getPage().setCurrentPage(currentPage);
        pageVO.getPage().setStartPage(startPage);
        pageVO.getPage().setEndPage(endPage);
        pageVO.getPage().setPrev(prev);
        pageVO.getPage().setNext(next);

        return pageVO;
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

    public List<BoardVO> findMyBordList(String createdByUuid) {
        List<Board> boardList = boardRepository.findByCreatedByUuidOrderByCreatedAtDesc(createdByUuid);
        List<BoardVO> boardVOList = new ArrayList<>();
        // EntityList -> VOList
        for(Board board : boardList) {
            BoardVO boardVO = modelMapper.map(board,BoardVO.class);
            boardVOList.add(boardVO);
        }
        return boardVOList;
    }
}
