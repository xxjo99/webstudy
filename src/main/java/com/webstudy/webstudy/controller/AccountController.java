package com.webstudy.webstudy.controller;

import com.webstudy.webstudy.entity.BoardEntity;
import com.webstudy.webstudy.entity.CommentEntity;
import com.webstudy.webstudy.repository.BoardRepository;
import com.webstudy.webstudy.service.AccountService;
import com.webstudy.webstudy.entity.UserEntity;
import com.webstudy.webstudy.service.BoardService;
import com.webstudy.webstudy.service.CommentService;
import com.webstudy.webstudy.validator.AccountValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private AccountValidator accountValidator;
    @Autowired
    private BoardRepository boardRepository;

    // 로그인 페이지 이동, 로그인 기능은 시큐리티로 위임
    @GetMapping("/login")
    public String login() {
        return "/account/login";
    }

    // 회원가입 페이지 이동
    @GetMapping("/join")
    public String join(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "/account/join";
    }

    // 회원가입
    @PostMapping("/join")
    public String join(@ModelAttribute("user") @Valid UserEntity user, BindingResult bindingResult, Model model) {

        // 입력값 검증
        accountValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/account/join";
        }

        accountService.saveUser(user);
        return "redirect:/";
    }

    // 마이페이지 이동, 해당 유저가 작성한 글, 댓글 리스트 조회
    @GetMapping("/user")
    public String getBoardAndCommentList(Model model, @RequestParam Long userId) {
        // 게시글 리스트
        List<BoardEntity> boardList = boardService.getBoardListByUserId(userId);
        model.addAttribute("boardList", boardList);
        // 댓글 리스트
        List<CommentEntity> commentList = commentService.getCommentListByUserId(userId);
        model.addAttribute("commentList", commentList);

        return "/account/user_page";
    }

}
