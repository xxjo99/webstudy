package com.webstudy.webstudy.repository;

import com.webstudy.webstudy.entity.BoardEntity;
import com.webstudy.webstudy.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query(value = "SELECT * FROM comment WHERE board_id = :boardId", nativeQuery = true)
    List<CommentEntity> findByBoardId(Long boardId); // 댓글 조회

    @Query(value = "SELECT * FROM comment WHERE user_id = :userId", nativeQuery = true)
    List<CommentEntity> findByUserId(Long userId); // 유저 id를 통해 조회

    CommentEntity findByCommentId(Long commentId);

}
