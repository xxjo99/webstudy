package com.webstudy.webstudy.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webstudy.webstudy.account.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "comment")
@Schema(description = "댓글 Model")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "댓글 id, pk", nullable = false)
    private Long commentId;

    @NotNull
    @Schema(description = "댓글 내용", nullable = false, example = "댓글 내용")
    private String commentContent;

    @NotNull
    @Schema(description = "댓글 작성자", nullable = false, example = "유저1")
    private String commentUser;

    @NotNull
    @Schema(description = "게시글 번호", nullable = false, example = "1 ")
    private Long boardId;
}
