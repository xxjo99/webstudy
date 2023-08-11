package com.webstudy.webstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodayWebtoonDetailDTO {
    private String title; // 웹툰 제목
    private String about; // 웹툰 정보
    private String genre; // 웹툰 장르
    private String age; // 연령
    private String thumb; // 썸네일 주소
}
