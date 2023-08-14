package com.webstudy.webstudy.controller;

import com.webstudy.webstudy.dto.TodayWebtoonDTO;
import com.webstudy.webstudy.dto.WebtoonDetailDTO;
import com.webstudy.webstudy.dto.WebtoonEpisodeDTO;
import com.webstudy.webstudy.dto.WebtoonDTO;
import com.webstudy.webstudy.service.WebtoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/webtoon")
public class WebtoonController {

    @Autowired
    private WebtoonService webtoonService;

    // 네이버웹툰
    @GetMapping("/naver")
    public String naverWebtoon(Model model) {
        // 오늘의 웹툰
        List<TodayWebtoonDTO> todayWebtoons = webtoonService.getTodayWebtoons();
        model.addAttribute("todayWebtoons", todayWebtoons);
        // 월
        List<WebtoonDTO> monWebtoons = webtoonService.getWebtoons(0, 100, "naver", "mon");
        model.addAttribute("monWebtoons", monWebtoons);
        // 화
        List<WebtoonDTO> tueWebtoons = webtoonService.getWebtoons(0, 100, "naver", "tue");
        model.addAttribute("tueWebtoons", tueWebtoons);
        // 수
        List<WebtoonDTO> wedWebtoons = webtoonService.getWebtoons(0, 100, "naver", "wed");
        model.addAttribute("wedWebtoons", wedWebtoons);
        // 목
        List<WebtoonDTO> thuWebtoons = webtoonService.getWebtoons(0, 100, "naver", "thu");
        model.addAttribute("thuWebtoons", thuWebtoons);
        // 금
        List<WebtoonDTO> friWebtoons = webtoonService.getWebtoons(0, 100, "naver", "fri");
        model.addAttribute("friWebtoons", friWebtoons);
        // 토
        List<WebtoonDTO> satWebtoons = webtoonService.getWebtoons(0, 100, "naver", "sat");
        model.addAttribute("satWebtoons", satWebtoons);
        // 일
        List<WebtoonDTO> sunWebtoons = webtoonService.getWebtoons(0, 100, "naver", "sun");
        model.addAttribute("sunWebtoons", sunWebtoons);
        // 완결
        List<WebtoonDTO> finishedWebtoons = webtoonService.getWebtoons(0, 100, "naver", "finished");
        model.addAttribute("finishedWebtoons", finishedWebtoons);
        // 데일리
        List<WebtoonDTO> dailyWebtoons = webtoonService.getWebtoons(0, 100, "naver", "naverDaily");
        model.addAttribute("dailyWebtoons", dailyWebtoons);

        return "/webtoon/naver_webtoon";
    }

    // 카카오 웹툰
    @GetMapping("/kakao")
    public String kakaoWebtoon(Model model) {
        // 월
        List<WebtoonDTO> monWebtoons = webtoonService.getWebtoons(0, 100, "kakao", "mon");
        model.addAttribute("monWebtoons", monWebtoons);
        // 화
        List<WebtoonDTO> tueWebtoons = webtoonService.getWebtoons(0, 100, "kakao", "tue");
        model.addAttribute("tueWebtoons", tueWebtoons);
        // 수
        List<WebtoonDTO> wedWebtoons = webtoonService.getWebtoons(0, 100, "kakao", "wed");
        model.addAttribute("wedWebtoons", wedWebtoons);
        // 목
        List<WebtoonDTO> thuWebtoons = webtoonService.getWebtoons(0, 100, "kakao", "thu");
        model.addAttribute("thuWebtoons", thuWebtoons);
        // 금
        List<WebtoonDTO> friWebtoons = webtoonService.getWebtoons(0, 100, "kakao", "fri");
        model.addAttribute("friWebtoons", friWebtoons);
        // 토
        List<WebtoonDTO> satWebtoons = webtoonService.getWebtoons(0, 100, "kakao", "sat");
        model.addAttribute("satWebtoons", satWebtoons);
        // 일
        List<WebtoonDTO> sunWebtoons = webtoonService.getWebtoons(0, 100, "kakao", "sun");
        model.addAttribute("sunWebtoons", sunWebtoons);
        // 완결
        List<WebtoonDTO> finishedWebtoons = webtoonService.getWebtoons(0, 100, "kakao", "finished");
        model.addAttribute("finishedWebtoons", finishedWebtoons);

        return "/webtoon/kakao_webtoon";
    }

    // 카카오페이지 웹툰
    @GetMapping("/kakao-page")
    public String kakaoPageWebtoon(Model model) {
        // 월
        List<WebtoonDTO> monWebtoons = webtoonService.getWebtoons(0, 100, "kakaoPage", "mon");
        model.addAttribute("monWebtoons", monWebtoons);
        // 화
        List<WebtoonDTO> tueWebtoons = webtoonService.getWebtoons(0, 100, "kakaoPage", "tue");
        model.addAttribute("tueWebtoons", tueWebtoons);
        // 수
        List<WebtoonDTO> wedWebtoons = webtoonService.getWebtoons(0, 100, "kakaoPage", "wed");
        model.addAttribute("wedWebtoons", wedWebtoons);
        // 목
        List<WebtoonDTO> thuWebtoons = webtoonService.getWebtoons(0, 100, "kakaoPage", "thu");
        model.addAttribute("thuWebtoons", thuWebtoons);
        // 금
        List<WebtoonDTO> friWebtoons = webtoonService.getWebtoons(0, 100, "kakaoPage", "fri");
        model.addAttribute("friWebtoons", friWebtoons);
        // 토
        List<WebtoonDTO> satWebtoons = webtoonService.getWebtoons(0, 100, "kakaoPage", "sat");
        model.addAttribute("satWebtoons", satWebtoons);
        // 일
        List<WebtoonDTO> sunWebtoons = webtoonService.getWebtoons(0, 100, "kakaoPage", "sun");
        model.addAttribute("sunWebtoons", sunWebtoons);
        // 완결
        List<WebtoonDTO> finishedWebtoons = webtoonService.getWebtoons(0, 100, "kakaoPage", "finished");
        model.addAttribute("finishedWebtoons", finishedWebtoons);

        return "/webtoon/kakao_page_webtoon";
    }

    // 검색
    @GetMapping("/search")
    public String searchWebtoon(@RequestParam String keyword, Model model) {
        List<WebtoonDTO> searchWebtoonList = webtoonService.searchWebtoon(keyword);
        model.addAttribute("searchWebtoonList", searchWebtoonList);
        return "/webtoon/search_webtoon";
    }

    // 네이버웹툰 에피소드
    @GetMapping("/naver/list")
    public String naverWebtoonEpisode(@RequestParam String webtoonId, Model model) {
        // 웹툰 정보
        WebtoonDetailDTO webtoon = webtoonService.getWebtoonDetail(webtoonId);
        model.addAttribute("webtoon", webtoon);

        // 에피소드 리스트
        List<WebtoonEpisodeDTO> episodes = webtoonService.getWebtoonEpisodes(webtoonId);
        model.addAttribute("episodes", episodes);

        return "/webtoon/naver_webtoon_episodes";
    }

}
