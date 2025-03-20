package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/memos") //prefix
public class MemoController {

    // DB 역할 수행
    private final Map<Long, Memo> memoList = new HashMap<>();

    // 글 작성
    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto requestDto) {

        // 식별자 1씩 증가
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;

        // 요청받은 데이터로 Memo 객체 생성
        Memo memo = new Memo(memoId, requestDto.getTitle(), requestDto.getContents());

        // 메모 객체 생성
        memoList.put(memoId, memo);

        // 요청 > 응답
        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MemoResponseDto>> findAllMemos() {
        List<MemoResponseDto> responseDtoList;

        // HashMap<Memo> -> List<MemoResponseDto>
//        for (Memo memo : memoList.values()) {
//            MemoResponseDto responseDto = new MemoResponseDto(memo);
//            responseDtoList.add(responseDto);
//        }

        responseDtoList = memoList.values().stream()
                .map(MemoResponseDto::new)
                .toList();

        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }


    // 개별 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id) {

        Memo memo = memoList.get(id);
        // 조회할 데이터가 없는 경우
        if (memo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    // 전체 수정 기능
    @PutMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateMemoById(
            @PathVariable Long id,
            @RequestBody MemoRequestDto requestDto) {

        Memo memo = memoList.get(id);
        // 조회할 데이터가 없는 경우
        if (memo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 입력 데이터가 없는 경우
        if (requestDto.getTitle() == null || requestDto.getContents() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        memo.update(requestDto);

        // 응답
        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    // 제목 수정 기능
    @PatchMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateTitle(
            @PathVariable Long id,
            @RequestBody MemoRequestDto requestDto
    ) {
        Memo memo = memoList.get(id);

        // NULL 방지
        if (memo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 필수 값 검증
        if (requestDto.getTitle() == null || requestDto.getContents() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        memo.updateTitle(requestDto);
        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemoById(@PathVariable Long id) {

       if(memoList.containsKey(id)){
           memoList.remove(id);
       return new ResponseEntity<>(HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

