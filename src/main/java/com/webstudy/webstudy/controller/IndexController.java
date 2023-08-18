package com.webstudy.webstudy.controller;

import com.webstudy.webstudy.entity.UserEntity;
import com.webstudy.webstudy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class IndexController {

    @Autowired
    private AccountService accountService;

    // 홈페이지
    @GetMapping("/")
    public String indexForm() {
        return "/home/index";
    }

    // 포트폴리오
    @GetMapping("/project")
    public String portfolioForm() {
        return "/home/project";
    }

    // 컨택
    @GetMapping("/contact")
    public String contactForm() {
        return "/home/contact";
    }

    @GetMapping("/test")
    public String index(Model model, Principal principal) {

        if (principal != null) {
            String userName = principal.getName();
            UserEntity user = accountService.getUserByUserName(userName);
            if (user != null) {
                model.addAttribute("userId", user.getUserId());
            }
        }

        return "index";
    }

}
