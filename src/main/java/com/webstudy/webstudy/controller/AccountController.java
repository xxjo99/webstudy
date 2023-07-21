package com.webstudy.webstudy.controller;

import com.webstudy.webstudy.service.AccountService;
import com.webstudy.webstudy.entity.UserEntity;
import com.webstudy.webstudy.validator.AccountValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountValidator accountValidator;

    // 로그인 페이지 이동, 로그인 기능은 시큐리티로 위임
    @GetMapping("/login")
    public String login() {
        return "/account/login";
    }

    // 회원가입 페이지 이동
    @GetMapping("/register")
    public String register(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "/account/register";
    }

    // 회원가입
    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid UserEntity user, BindingResult bindingResult, Model model) {

        // 입력값 검증
        accountValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/account/register";
        }

        accountService.saveUser(user);
        return "redirect:/";
    }

}
