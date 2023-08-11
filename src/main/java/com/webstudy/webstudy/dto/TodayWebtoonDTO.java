package com.webstudy.webstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodayWebtoonDTO {
    private String webtoonId; // 웹툰 아이디
    private String title; // 웹툰 제목
    private String thumb; // 썸네일 주소
}
