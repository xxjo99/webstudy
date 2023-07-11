package com.webstudy.webstudy.board;

import com.webstudy.webstudy.validator.BoardValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String getBoardList(Model model, @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText) {
        Page<BoardEntity> boardList = boardService.getBoardList(searchText, pageable);

        int startPage = Math.max(1, boardList.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boardList.getTotalPages(), boardList.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("boardList", boardList);

        return "/board/boardlist";
    }

    @GetMapping("/form")
    public String getBoard(Model model, @RequestParam(required = false) Long boardId) {

        if (boardId == null) {
            model.addAttribute("board", new BoardEntity());
        } else {
            BoardEntity board = boardService.getBoard(boardId);
            model.addAttribute("board", board);
        }

        return "/board/boardform";
    }

    @PostMapping("/form")
    public String registerBoard(@ModelAttribute("board") @Valid BoardEntity board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/board/boardform";
        }

        boardService.registerBoard(board);
        return "redirect:/board/list";
    }

}
