package com.example.memo.dto;

import com.example.memo.entity.Memo;
import lombok.Getter;

// client에 데이터를 반환할 때 사용할 클래스
@Getter
public class MemoResponseDto {
    private Long id;
    private String title;
    private String contents;

    public MemoResponseDto(Memo memo){
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.contents = memo.getContent();
    }


}
