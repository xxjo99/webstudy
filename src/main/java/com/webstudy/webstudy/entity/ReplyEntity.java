package com.webstudy.webstudy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@Table(name = "reply")
@Schema(description = "게시글 Model")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "대댓글 id, pk", nullable = false)
    private Long replyId;

    @NotNull
    @Schema(description = "내용", nullable = false, example = "내용")
    private String content;

    @Schema(description = "생성날짜", nullable = false, example = "0000-00-00 00:00:00")
    private Timestamp createDate;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    @JsonIgnore
    private CommentEntity comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;
}
