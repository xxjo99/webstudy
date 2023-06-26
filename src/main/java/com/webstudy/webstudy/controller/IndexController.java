package com.webstudy.webstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("")
    public String hello(Model model) {
        model.addAttribute("data", "서버쪽에서 전손");
        return "index.html";
    }
}
