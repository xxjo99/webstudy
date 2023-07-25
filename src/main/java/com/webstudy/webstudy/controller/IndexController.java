package com.webstudy.webstudy.controller;

import com.webstudy.webstudy.entity.UserEntity;
import com.webstudy.webstudy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class IndexController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
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
