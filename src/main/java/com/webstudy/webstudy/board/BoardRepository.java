package com.webstudy.webstudy.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    BoardEntity findByBoardId(Long boardId); // boardId를 통해 조회

}
