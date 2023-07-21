package com.webstudy.webstudy.controller;

import com.webstudy.webstudy.entity.CommentEntity;
import com.webstudy.webstudy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 등록
    @PostMapping("/register")
    public String registerComment(CommentEntity comment, Authentication authentication, @RequestParam Long boardId) {
        commentService.registerComment(comment, boardId, authentication);
        return "redirect:/board/detail?boardId=" + boardId;
    }


}
