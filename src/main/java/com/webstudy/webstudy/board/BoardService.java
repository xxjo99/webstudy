package com.webstudy.webstudy.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 게시글 목록 조회, 페이징 적용, 검색 적용
    public Page<BoardEntity> getBoardList(String searchText, Pageable pageable) {
        return boardRepository.findByTitleContainingOrContentContainingOrUserContaining(searchText, searchText, searchText, pageable);
    }

    // 게시글 등록
    public void registerBoard(BoardEntity board) {
        board.setUser("미정");
        boardRepository.save(board);
    }

    // 게시글 조회
    public BoardEntity getBoard(Long boardId) {
        return boardRepository.findByBoardId(boardId);
    }

}
