package com.webstudy.webstudy;

import com.webstudy.webstudy.dto.TodayWebtoonDetailDTO;
import com.webstudy.webstudy.dto.TodayWebtoonEpisodeDTO;
import com.webstudy.webstudy.service.WebtoonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WebstudyApplicationTests {

	@Autowired
	private WebtoonService webtoonService;

	@Test
	void getTodayWebtoonDetail() {
		TodayWebtoonDetailDTO todayWebtoonDetailDTO = webtoonService.getTodayWebtoonDetail("790713");
		System.out.println(todayWebtoonDetailDTO.getTitle());
	}

	@Test
	void getTodayWebtoonEpisodes() {
		List<TodayWebtoonEpisodeDTO> todayWebtoonEpisodes = webtoonService.getTodayWebtoonEpisodes("790713");

		for (TodayWebtoonEpisodeDTO todayWebtoonEpisode : todayWebtoonEpisodes) {
			System.out.println(todayWebtoonEpisode);
		}
	}

}
