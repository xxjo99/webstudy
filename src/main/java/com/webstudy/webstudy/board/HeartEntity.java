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
@Table(name = "heart")
@Schema(description = "좋아요 Model")
public class HeartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "좋아요 id, pk", nullable = false)
    private Long heartId;

    @NotNull
    @Schema(description = "게시글 id", nullable = false)
    private Long boardId;

    @NotNull
    @Schema(description = "유저 id, pk", nullable = false)
    private Long userId;
}
