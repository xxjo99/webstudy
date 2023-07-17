package com.webstudy.webstudy.board;

import com.webstudy.webstudy.validator.BoardValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardValidator boardValidator;

    // 게시글 조회
    @GetMapping("/boards")
    public String getBoardList(Model model, @PageableDefault(size = 10) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText) {
        Page<BoardEntity> boardList = boardService.getBoardListWithPaging(searchText, pageable);

        int startPage = Math.max(1, boardList.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boardList.getTotalPages(), boardList.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("boardList", boardList);

        return "/board/boardlist";
    }

    // 게시글 상세페이지 조회
    @GetMapping("/detail")
    public String getBoardDetail(Model model, @RequestParam Long boardId) {
        BoardEntity board = boardService.getBoard(boardId);
        boardService.increaseView(board); // 게시글 조회수 증가
        model.addAttribute("board", board);
        return "/board/boarddetail";
    }

    // 게시글 등록 페이지 이동
    @GetMapping("/form")
    public String getBoard(Model model, @RequestParam(required = false) Long boardId) {

        if (boardId == null) { // boardId가 없을경우
            model.addAttribute("board", new BoardEntity());
        } else { // boardId가 있을경우
            BoardEntity board = boardService.getBoard(boardId);
            model.addAttribute("board", board);
        }

        return "/board/boardform";
    }

    // 게시글 등록
    @PostMapping("/form")
    public String registerBoard(@Valid BoardEntity board, BindingResult bindingResult, Authentication authentication) {

        // 입력값 검증
        boardValidator.validate(board, bindingResult);

        // 올바르지 않은 입력값일시 에러발생
        if (bindingResult.hasErrors()) {
            return "/board/boardform";
        }

        // 게시글 저장
        boardService.registerBoard(board, authentication);
        return "redirect:/board/boards";
    }

    // 게시글 삭제
    @GetMapping("/delete")
    public String deleteBoard(Long boardId) {
        boardService.deleteBoard(boardId);
        return "redirect:/board/boards";
    }

}
