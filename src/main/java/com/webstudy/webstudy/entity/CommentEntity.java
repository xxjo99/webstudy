package com.webstudy.webstudy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String content;

    @Schema(description = "게시글", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @JsonIgnore
    private BoardEntity board;

    @NotNull
    @Schema(description = "댓글 작성자", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;
}
