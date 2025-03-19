package com.example.memo.dto;

import lombok.Getter;

// client에 데이터를 반환할 때 사용할 클래스
@Getter
public class MemoResponseDto {
    private Long id;
    private String title;
    private String contents;

}
