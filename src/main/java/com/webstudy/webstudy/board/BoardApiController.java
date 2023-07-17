package com.webstudy.webstudy.board;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "게시글 api")
@RestController
@RequestMapping("/api/board")
class BoardApiController {

    @Autowired
    private BoardService boardService;

    @Operation(summary = "모든 게시글 조회", description = "제목, 내용을 포함해서 검색 가능")
    @GetMapping("/boards")
    public List<BoardEntity> getBoardList(
            @Parameter(description = "검색할 제목") @DefaultValue("기본값 없음") @RequestParam(required = false, defaultValue = "") String title,
            @Parameter(description = "검색할 타이틀") @DefaultValue("기본값 없음") @RequestParam(required = false, defaultValue = "") String content)
    {
        return boardService.getBoardList(title, content);
    }

    @Operation(summary = "게시글 등록")
    @PostMapping("/form")
    public BoardEntity registerBoard(@Parameter(description = "등록할 게시글") @RequestBody BoardEntity board) {
        return boardService.registerBoard(board);
    }

    @Operation(summary = "특정 게시글 조회")
    @GetMapping("/boards/{boardId}")
    public BoardEntity getBoard(@Parameter(description = "조회할 게시글 아이디")  @PathVariable Long boardId) {
        return boardService.getBoard(boardId);
    }

    @Operation(summary = "게시글 수정", description = "게시글 수정, 게시글이 존재하지 않는다면 새로운 게시글 등록")
    @PutMapping("/boards/{boardId}")
    public BoardEntity modifyBoard(
            @Parameter(description = "수정할 게시글") @RequestBody BoardEntity newBoard,
            @Parameter(description = "수정할 게시글 아이디") @PathVariable Long boardId)
    {
        return boardService.modifyBoard(newBoard, boardId);
    }

    // 게시글 삭제
    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/boards/{boardId}")
    public void deleteBoard(@Parameter(description = "삭제할 게시글") @PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
    }
}