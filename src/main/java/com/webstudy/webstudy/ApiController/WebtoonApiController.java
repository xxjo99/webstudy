package com.webstudy.webstudy.ApiController;

import com.webstudy.webstudy.dto.WebtoonDTO;
import com.webstudy.webstudy.service.WebtoonService;
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

    @GetMapping("/webtoons")
    public List<WebtoonDTO> getWebtoons(
            @Parameter(description = "페이지 번호") @DefaultValue("0") @RequestParam(required = false, defaultValue = "0") int page,
            @Parameter(description = "한 페이지 결과 수") @DefaultValue("10") @RequestParam(required = false, defaultValue = "10") int perPage,
            @Parameter(description = "웹툰 공급자 - naver : 네이버 웹툰, kakao : 카카오 웹툰, kakaoPage : 카카오 페이지") @DefaultValue("모든 웹툰공급자") @RequestParam(required = false) String service,
            @Parameter(description = "업데이트 요일 - mon, tue, wed, thu, fri, sat, sun, finished, naverDaily") @DefaultValue("모든 요일") @RequestParam(required = false) String updateDay)
    {
        return webtoonService.getWebtoons(page, perPage, service, updateDay);
    }

    @GetMapping("/search")
    public List<WebtoonDTO> searchWebtoon(
            @Parameter(description = "검색 키워드") @DefaultValue("-") String keyword)
    {
        return webtoonService.searchWebtoon(keyword);
    }

}
