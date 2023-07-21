package com.webstudy.webstudy.repository;

import com.webstudy.webstudy.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    BoardEntity findByBoardId(Long boardId); // boardId를 통해 조회

    List<BoardEntity> findByTitleOrContent(String title, String content); // 제목과 내용을 통해 조회

    Page<BoardEntity> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable); // 조회, 검색기능 추가

    

}
