package com.webstudy.webstudy.service;

import com.webstudy.webstudy.entity.BoardEntity;
import com.webstudy.webstudy.entity.CommentEntity;
import com.webstudy.webstudy.entity.UserEntity;
import com.webstudy.webstudy.repository.BoardRepository;
import com.webstudy.webstudy.repository.CommentRepository;
import com.webstudy.webstudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    // 댓글 등록
    public void registerComment(CommentEntity comment, Long boardId, Authentication authentication) {

        // 게시글 저장
        BoardEntity board = boardRepository.findByBoardId(boardId);
        comment.setBoard(board);

        // 유저 저장
        String userName = authentication.getName();
        UserEntity user = userRepository.findByUserName(userName);
        comment.setUser(user);

        // 날짜 저장
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        comment.setCreateDate(timestamp);

        commentRepository.save(comment);
    }

    // 댓글 조회
    public List<CommentEntity> getCommentList(Long boardId) {
        return commentRepository.findByBoardId(boardId);
    }

    // 해당 유저가 작성한 게시글 조회
    public List<CommentEntity> getCommentListByUserId(Long userId) {
        return commentRepository.findByUserId(userId); // 작성한 게시글 리스트
    }

}
