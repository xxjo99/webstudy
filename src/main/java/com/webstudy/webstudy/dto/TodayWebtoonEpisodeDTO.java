package com.webstudy.webstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodayWebtoonEpisodeDTO {
    private String thumb; // 썸네일 주소
    private String id; // 에피소드 아이디
    private String title; // 에피소드 제목
    private String rating; // 별점
    private String date; // 업로드 날짜
}
