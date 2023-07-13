package com.webstudy.webstudy.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "계정 api", description = "유저, 권한 관련 api")
@RestController
@RequestMapping("/api/account")
public class AccountApiController {

    @Autowired
    private AccountService accountService;

    @Operation(summary = "유저 저장")
    @PostMapping("/register")
    public void saveUser(@Parameter(description = "저장할 유저 정보") UserEntity user) {
        accountService.saveUser(user);
    }

    @Operation(summary = "모든 유저 조회")
    @GetMapping("/users")
    public List<UserEntity> getUserList() {
        return accountService.getUserList();
    }

    @Operation(summary = "아이디를 통해 특정 유저 조회")
    @GetMapping("/users/id/{userId}")
    public UserEntity getUserByUserId(@Parameter(description = "검색할 유저 id") Long userId) {
        return accountService.getUserByUserId(userId);
    }

    @Operation(summary = "이름을 통해 특정 유저 조회")
    @GetMapping("/users/name/{userName}")
    public UserEntity getUserByUserName(@Parameter(description = "검색할 유저 이름") String userName) {
        return accountService.getUserByUserName(userName);
    }

}
