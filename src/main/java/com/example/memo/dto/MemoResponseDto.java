package com.example.memo.dto;

import com.example.memo.entity.Memo;
import lombok.Getter;

// client에 데이터를 반환할 때 사용할 클래스
// 응답
@Getter
public class MemoResponseDto {
    private Long id;    // 필요에 따라 포함
    private String title;
    private String contents;

    public MemoResponseDto(Memo memo){
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.contents = memo.getContents();
    }


}
