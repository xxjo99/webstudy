package com.webstudy.webstudy.repository;

import com.webstudy.webstudy.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query(value = "SELECT * FROM comment WHERE board_id = :boardId", nativeQuery = true)
    List<CommentEntity> findByBoardId(Long boardId); // 댓글 조회

}
