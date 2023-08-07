package com.webstudy.webstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/webtoon")
public class WebtoonController {

    // 홈
    @GetMapping("/home")
    public String contactForm() {
        return "/webtoon/webtoon";
    }
}
