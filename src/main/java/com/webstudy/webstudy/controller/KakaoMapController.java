package com.webstudy.webstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kakao")
public class KakaoMapController {

    @GetMapping("/map")
    public String kakaoMap() {
        return "/kakaomap/kakaomap";
    }
}
