package com.example.memo.dto;

// 클라이언트의 요청을 받아주는 클래스

import lombok.Getter;

@Getter
public class MemoRequestDto {
        private String title;
        private String content;
}
