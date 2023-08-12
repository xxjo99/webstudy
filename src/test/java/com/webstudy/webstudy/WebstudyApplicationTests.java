package com.webstudy.webstudy;

import com.webstudy.webstudy.dto.WebtoonDetailDTO;
import com.webstudy.webstudy.dto.WebtoonEpisodeDTO;
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
		WebtoonDetailDTO webtoonDetailDTO = webtoonService.getWebtoonDetail("790713");
		System.out.println(webtoonDetailDTO.getTitle());
	}

	@Test
	void getTodayWebtoonEpisodes() {
		List<WebtoonEpisodeDTO> todayWebtoonEpisodes = webtoonService.getWebtoonEpisodes("790713");

		for (WebtoonEpisodeDTO todayWebtoonEpisode : todayWebtoonEpisodes) {
			System.out.println(todayWebtoonEpisode);
		}
	}

}
