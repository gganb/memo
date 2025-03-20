package com.example.memo.dto;

// 클라이언트의 요청을 받아주는 클래스

import lombok.Getter;

// 요청 데이터를 처리하는 객체
@Getter
public class MemoRequestDto {
        // 요청 받을 데이터
        private String title;
        private String contents;
}
