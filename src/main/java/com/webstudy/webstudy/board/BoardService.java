package com.webstudy.webstudy.board;

import com.webstudy.webstudy.account.UserEntity;
import com.webstudy.webstudy.account.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    // 모든 게시글 조회
    public List<BoardEntity> getBoardList(String title, String content) {
        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return boardRepository.findAll();
        } else {
            return boardRepository.findByTitleOrContent(title, content);
        }
    }

    // 모든 게시글 조회, 페이징, 검색 적용
    public Page<BoardEntity> getBoardListWithPaging(String searchText, Pageable pageable) {
        return boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
    }

    // 게시글 조회
    public BoardEntity getBoard(Long boardId) {
        return boardRepository.findByBoardId(boardId);
    }

    // 게시글 조회수 증가
    @Transactional
    public BoardEntity increaseView(BoardEntity board) {
        board.setView(board.getView() + 1);
        return board;
    }

    // 게시글 등록, 이름 제외, api에서 사용
    public BoardEntity registerBoard(BoardEntity board) {
        return boardRepository.save(board);
    }

    // 게시글 등록
    public void registerBoard(BoardEntity board, Authentication authentication) {
        // 스프링 시큐리티에서 관리하는 유저의 이름을 통해 유저 검색 후 저장
        String userName = authentication.getName();
        UserEntity user = userRepository.findByUserName(userName);
        board.setUser(user);

        // 게시글 저장
        board.setView(0);
        boardRepository.save(board);
    }

    // 게시글 수정
    public BoardEntity modifyBoard(BoardEntity newBoard, Long boardId) {

        return boardRepository.findById(boardId)
                .map(board -> { // 존재하는 게시글일경우 수정
                    board.setTitle(newBoard.getTitle());
                    board.setContent(newBoard.getContent());
                    return boardRepository.save(board);
                })
                .orElseGet(() -> { // 게시글이 존재하지 않는다면 새로운 게시글 등록
                    newBoard.setBoardId(boardId);
                    return boardRepository.save(newBoard);
                });
    }

    // 게시글 삭제
    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

}
