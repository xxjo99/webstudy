package com.webstudy.webstudy.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.stream.Collectors;

@Service
public class WebtoonService {

    // 기본 URL
    private static final String BASE_URL = "https://korea-webtoon-api.herokuapp.com";

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
    public String getWebtoons(int page, int perPage, String service, String updateDay) {
        try {
            // 주소 구성
            URI uri = new URI(BASE_URL + "?" +
                    "page=" + page +
                    "&perPage=" + perPage +
                    "&service=" + service +
                    "&updateDay=" + updateDay);

            String jsonResponse = performHttpGet(uri);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // GET요청 수행, 결과값 반환
            return performHttpGet(uri);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // 웹툰 검색
    public String searchWebtoon(String keyword) {
        try {
            // 주소 구성
            URI uri = new URI(BASE_URL + "/search" +
                    "?keyword=" + keyword);

            // GET요청 수행, 결과값 반환
            return performHttpGet(uri);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
