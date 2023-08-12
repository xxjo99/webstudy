package com.webstudy.webstudy.ApiController;

import com.webstudy.webstudy.dto.TodayWebtoonDTO;
import com.webstudy.webstudy.dto.WebtoonDetailDTO;
import com.webstudy.webstudy.dto.WebtoonEpisodeDTO;
import com.webstudy.webstudy.dto.WebtoonDTO;
import com.webstudy.webstudy.service.WebtoonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "웹툰 api")
@RestController
@RequestMapping("/api/webtoon")
public class WebtoonApiController {

    @Autowired
    private WebtoonService webtoonService;

    @Operation(summary = "웹툰 조회", description = "100개의 웹툰 조회")
    @GetMapping("/webtoons")
    public List<WebtoonDTO> getWebtoons(
            @Parameter(description = "페이지 번호") @DefaultValue("0") @RequestParam(required = false, defaultValue = "0") int page,
            @Parameter(description = "한 페이지 결과 수") @DefaultValue("10") @RequestParam(required = false, defaultValue = "10") int perPage,
            @Parameter(description = "웹툰 공급자 - naver : 네이버 웹툰, kakao : 카카오 웹툰, kakaoPage : 카카오 페이지") @DefaultValue("모든 웹툰공급자") @RequestParam(required = false) String service,
            @Parameter(description = "업데이트 요일 - mon, tue, wed, thu, fri, sat, sun, finished, naverDaily") @DefaultValue("모든 요일") @RequestParam(required = false) String updateDay)
    {
        System.out.println("run");
        return webtoonService.getWebtoons(page, perPage, service, updateDay);
    }

    @Operation(summary = "웹툰 검색")
    @GetMapping("/search")
    public List<WebtoonDTO> searchWebtoon(
            @Parameter(description = "검색 키워드") @DefaultValue("-") String keyword)
    {
        return webtoonService.searchWebtoon(keyword);
    }

    @Operation(summary = "오늘의 웹툰 조회")
    @GetMapping("/today")
    public List<TodayWebtoonDTO> getTodayWebtoons() {
        return webtoonService.getTodayWebtoons();
    }

    @Operation(summary = "웹툰 상세 페이지 조회")
    @GetMapping("/detail")
    public WebtoonDetailDTO getTodayWebtoonDetail(@Parameter(description = "웹툰 아이디") @RequestParam String webtoonId) {
        return webtoonService.getWebtoonDetail(webtoonId);
    }

    @Operation(summary = "웹툰 에피소드 조회")
    @GetMapping("/episodes")
    public List<WebtoonEpisodeDTO> getTodayWebtoonEpisodes(@Parameter(description = "웹툰 아이디") @RequestParam String webtoonId) {
        return webtoonService.getWebtoonEpisodes(webtoonId);
    }

}
