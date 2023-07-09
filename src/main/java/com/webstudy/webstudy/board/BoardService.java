package com.webstudy.webstudy.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 게시글 목록 조회
    public List<BoardEntity> getBoardList() {
        return boardRepository.findAll();
    }

    // 게시글 등록
    public void registerBoard(BoardEntity board) {
        board.setAuthor("미정");
        boardRepository.save(board);
    }

    // 게시글 조회
    public BoardEntity getBoard(Long boardId) {
        return boardRepository.findByBoardId(boardId);
    }

}
