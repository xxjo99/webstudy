package com.webstudy.webstudy.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webstudy.webstudy.dto.WebtoonDTO;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebtoonService {

    // 기본 Api URL
    private static final String BASE_URL = "https://korea-webtoon-api.herokuapp.com";
    private static final String BASE_URL_SEARCH = "https://korea-webtoon-api.herokuapp.com/search";

    // GET요청 수행, 결과값 반환
    private String performHttpGet(URI uri) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
        conn.setRequestMethod("GET");

        // 결과값을 한줄씩 저장해서 문자열로 반환
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            return br.lines().collect(Collectors.joining("\n"));
        } finally {
            conn.disconnect(); // 연결 종료
        }
    }

    // 웹툰 리스트
    public List<WebtoonDTO> getWebtoons(int page, int perPage, String service, String updateDay) {
        try {
            // 주소 구성
            URI uri = new URI(BASE_URL + "?" +
                    "page=" + page +
                    "&perPage=" + perPage +
                    "&service=" + service +
                    "&updateDay=" + updateDay);

            // GET 요청 수행
            String jsonResponse = performHttpGet(uri);

            // JSON 응답 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // 필요한 데이터만 뽑아서 객체로 변환
            List<WebtoonDTO> webtoons = new ArrayList<>();
            for (JsonNode webtoonNode : rootNode.path("webtoons")) {
                WebtoonDTO webtoon = new WebtoonDTO();

                webtoon.setWebtoonId(webtoonNode.path("webtoonId").asText());
                webtoon.setTitle(webtoonNode.path("title").asText());
                webtoon.setAuthor(webtoonNode.path("author").asText());
                webtoon.setUrl(webtoonNode.path("url").asText());
                webtoon.setImg(webtoonNode.path("img").asText());
                webtoon.setService(webtoonNode.path("service").asText());

                List<String> updateDays = new ArrayList<>();
                for (JsonNode updateNode : webtoonNode.path("updateDays")) {
                    updateDays.add(updateNode.asText());
                }
                webtoon.setUpdateDays(updateDays);

                webtoons.add(webtoon);
            }

            // 결과값 반환
            return webtoons;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // 웹툰 검색
    public List<WebtoonDTO> searchWebtoon(String keyword) {
        try {
            // 키워드 인코딩
            String encodeKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
            // 주소 구성
            URI uri = new URI(BASE_URL_SEARCH + "?" +
                    "keyword=" + encodeKeyword);

            // GET 요청 수행
            String jsonResponse = performHttpGet(uri);

            // JSON 응답 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // 필요한 데이터만 뽑아서 객체로 변환
            List<WebtoonDTO> webtoons = new ArrayList<>();
            for (JsonNode webtoonNode : rootNode.path("webtoons")) {
                WebtoonDTO webtoon = new WebtoonDTO();

                webtoon.setWebtoonId(webtoonNode.path("webtoonId").asText());
                webtoon.setTitle(webtoonNode.path("title").asText());
                webtoon.setAuthor(webtoonNode.path("author").asText());
                webtoon.setUrl(webtoonNode.path("url").asText());
                webtoon.setImg(webtoonNode.path("img").asText());
                webtoon.setService(webtoonNode.path("service").asText());

                List<String> updateDays = new ArrayList<>();
                for (JsonNode updateNode : webtoonNode.path("updateDays")) {
                    updateDays.add(updateNode.asText());
                }
                webtoon.setUpdateDays(updateDays);

                webtoons.add(webtoon);
            }

            // 결과값 반환
            return webtoons;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
