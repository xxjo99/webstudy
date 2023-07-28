package com.webstudy.webstudy.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "user")
@Schema(description = "사용자 Model")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "유저 id, pk", nullable = false)
    private Long userId;

    @Schema(description = "유저 이름", nullable = false, example = "user1")
    @Pattern(regexp = "^[a-z0-9]{5,30}$", message = "5자 이상의 영문과 숫자가 포함 ")
    private String userName;

    @Schema(description = "유저 패스워드, 암호화 적용", nullable = false, example = "password")
    private String password;
    
    @Schema(description = "계정 활성화 상태", nullable = false, defaultValue = "1", allowableValues = {"1", "0"})
    private Boolean enabled;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roleList = new ArrayList<>();

}
