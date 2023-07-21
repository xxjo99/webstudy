package com.webstudy.webstudy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "role")
@Schema(description = "권한 Model")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "권한 id, pk", nullable = false)
    private Long roleId;

    @Schema(description = "권한 이름", nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roleList")
    @JsonIgnore
    private List<UserEntity> userList;

}
