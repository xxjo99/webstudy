package com.webstudy.webstudy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webstudy.webstudy.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "board")
@Schema(description = "게시글 Model")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "게시글 id, pk", nullable = false)
    private Long boardId;

    @NotNull
    @Schema(description = "제목", nullable = false, example = "제목")
    private String title;

    @NotNull
    @Schema(description = "내용", nullable = false, example = "내용")
    private String content;

    @Schema(description = "조회수", nullable = false, example = "100")
    private int view;

    @Schema(description = "생성날짜", nullable = false, example = "0000-00-00 00:00:00")
    private Timestamp createDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;
}
