package com.webstudy.webstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebtoonDTO {
    private String webtoonId; // 웹툰 아이디
    private String title; // 웹툰 제목
    private String author; // 웹툰 작가
    private String url; // 웹툰 주소
    private String img; // 썸네일 주소
    private String service; // 제공 플랫폼
    private List<String> updateDays; // 업로드 요일
}
