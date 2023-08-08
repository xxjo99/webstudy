package com.webstudy.webstudy.controller;

import com.webstudy.webstudy.dto.WebtoonDTO;
import com.webstudy.webstudy.service.WebtoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/webtoon")
public class WebtoonController {

    @Autowired
    private WebtoonService webtoonService;

    // 네이버웹툰 홈
    @GetMapping("/home/naver")
    public String naverHome(Model model) {
        // 월
        List<WebtoonDTO> monNaverWebtoons = webtoonService.getWebtoons(0, 100, "naver", "mon");
        model.addAttribute("monNaverWebtoons", monNaverWebtoons);
        // 화
        List<WebtoonDTO> tueNaverWebtoons = webtoonService.getWebtoons(0, 100, "naver", "tue");
        model.addAttribute("tueNaverWebtoons", tueNaverWebtoons);
        // 수
        List<WebtoonDTO> wedNaverWebtoons = webtoonService.getWebtoons(0, 100, "naver", "wed");
        model.addAttribute("wedNaverWebtoons", wedNaverWebtoons);
        // 목
        List<WebtoonDTO> thuNaverWebtoons = webtoonService.getWebtoons(0, 100, "naver", "thu");
        model.addAttribute("thuNaverWebtoons", thuNaverWebtoons);
        // 금
        List<WebtoonDTO> friNaverWebtoons = webtoonService.getWebtoons(0, 100, "naver", "fri");
        model.addAttribute("friNaverWebtoons", friNaverWebtoons);
        // 토
        List<WebtoonDTO> satNaverWebtoons = webtoonService.getWebtoons(0, 100, "naver", "sat");
        model.addAttribute("satNaverWebtoons", satNaverWebtoons);
        // 일
        List<WebtoonDTO> sunNaverWebtoons = webtoonService.getWebtoons(0, 100, "naver", "sun");
        model.addAttribute("sunNaverWebtoons", sunNaverWebtoons);
        // 완결
        List<WebtoonDTO> finishedNaverWebtoons = webtoonService.getWebtoons(0, 100, "naver", "finished");
        model.addAttribute("finishedNaverWebtoons", finishedNaverWebtoons);
        // 데일리
        List<WebtoonDTO> dailyNaverWebtoons = webtoonService.getWebtoons(0, 100, "naver", "naverDaily");
        model.addAttribute("dailyNaverWebtoons", dailyNaverWebtoons);

        return "/webtoon/webtoon";
    }
}
