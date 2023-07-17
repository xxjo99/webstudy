package com.webstudy.webstudy.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // 댓글 조회
    public List<CommentEntity> getCommentList(Long boardId) {
        return commentRepository.findByBoardId(boardId);
    }
}
