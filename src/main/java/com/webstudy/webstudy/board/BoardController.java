package com.webstudy.webstudy.board;

import com.webstudy.webstudy.validator.BoardValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getBoardList(Model model) {
        List<BoardEntity> boardList = boardService.getBoardList();
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
