package com.webstudy.webstudy.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webstudy.webstudy.account.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;
}
