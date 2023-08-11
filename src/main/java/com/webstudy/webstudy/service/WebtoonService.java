package com.webstudy.webstudy.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webstudy.webstudy.dto.TodayWebtoonDTO;
import com.webstudy.webstudy.dto.TodayWebtoonDetailDTO;
import com.webstudy.webstudy.dto.TodayWebtoonEpisodeDTO;
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
    private static final String BASE_URL_NOMARD = "https://webtoon-crawler.nomadcoders.workers.dev";

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

                String webtoonId = webtoonNode.path("webtoonId").asText();
                switch (service) {
                    case "naver" -> {
                        webtoonId = webtoonId.substring(webtoonId.length() - 6);
                        webtoon.setWebtoonId(webtoonId);
                    }
                    case "kakao" -> {
                        webtoonId = webtoonId.substring(webtoonId.length() - 4);
                        webtoon.setWebtoonId(webtoonId);
                    }
                    case "kakaoPage" -> {
                        webtoonId = webtoonId.substring(webtoonId.length() - 8);
                        webtoon.setWebtoonId(webtoonId);
                    }
                }

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

                String service = webtoonNode.path("service").asText();
                String webtoonId = webtoonNode.path("webtoonId").asText();

                switch (service) {
                    case "naver" -> {
                        webtoonId = webtoonId.substring(webtoonId.length() - 6);
                        webtoon.setWebtoonId(webtoonId);
                    }
                    case "kakao" -> {
                        webtoonId = webtoonId.substring(webtoonId.length() - 4);
                        webtoon.setWebtoonId(webtoonId);
                    }
                    case "kakaoPage" -> {
                        webtoonId = webtoonId.substring(webtoonId.length() - 8);
                        webtoon.setWebtoonId(webtoonId);
                    }
                }

                webtoon.setTitle(webtoonNode.path("title").asText());
                webtoon.setAuthor(webtoonNode.path("author").asText());
                webtoon.setUrl(webtoonNode.path("url").asText());
                webtoon.setImg(webtoonNode.path("img").asText());
                webtoon.setService(service);

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

    // 오늘의 웹툰
    public List<TodayWebtoonDTO> getTodayWebtoons() {
        try {
            // 주소 구성
            URI uri = new URI(BASE_URL_NOMARD + "/today");

            // GET 요청 수행
            String jsonResponse = performHttpGet(uri);

            // JSON 응답 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // 객체로 변환
            List<TodayWebtoonDTO> todayWebtoons = new ArrayList<>();
            for (JsonNode webtoonNode : rootNode) {
                TodayWebtoonDTO webtoon = new TodayWebtoonDTO();

                webtoon.setWebtoonId(webtoonNode.path("id").asText());
                webtoon.setTitle(webtoonNode.path("title").asText());
                webtoon.setThumb(webtoonNode.path("thumb").asText());

                todayWebtoons.add(webtoon);
            }

            // 결과값 반환
            return todayWebtoons;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 웹툰 상세 조회
    public TodayWebtoonDetailDTO getTodayWebtoonDetail(String webtoonId) {
        try {
            // 주소 구성
            URI uri = new URI(BASE_URL_NOMARD + "/" + webtoonId);

            // GET 요청 수행
            String jsonResponse = performHttpGet(uri);

            // JSON 응답 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // 객체로 변환 후 반환
            return objectMapper.treeToValue(rootNode, TodayWebtoonDetailDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 웹툰 에피소드 조회
    public List<TodayWebtoonEpisodeDTO> getTodayWebtoonEpisodes(String webtoonId) {
        try {
            // 주소 구성
            URI uri = new URI(BASE_URL_NOMARD + "/" + webtoonId + "/episodes");

            // GET 요청 수행
            String jsonResponse = performHttpGet(uri);

            // JSON 응답 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // 객체로 변환
            List<TodayWebtoonEpisodeDTO> todayWebtoonEpisodes = new ArrayList<>();
            for (JsonNode webtoonNode : rootNode) {
                TodayWebtoonEpisodeDTO episode = new TodayWebtoonEpisodeDTO();

                episode.setThumb(webtoonNode.path("thumb").asText());
                episode.setId(webtoonNode.path("id").asText());
                episode.setTitle(webtoonNode.path("title").asText());
                episode.setRating(webtoonNode.path("rating").asText());
                episode.setDate(webtoonNode.path("date").asText());

                todayWebtoonEpisodes.add(episode);
            }

            // 결과값 반환
            return todayWebtoonEpisodes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
