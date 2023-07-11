package com.webstudy.webstudy.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    BoardEntity findByBoardId(Long boardId); // boardId를 통해 조회

    Page<BoardEntity> findByTitleContainingOrContentContainingOrUserContaining(String title, String content, String user, Pageable pageable); // 조회, 검색기능 추가

}
