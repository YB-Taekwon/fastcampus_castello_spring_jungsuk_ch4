package com.ian.ch4.service;

import com.ian.ch4.domain.BoardDto;
import com.ian.ch4.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface BoardService {
    // 게시글 등록
    BoardDto save(BoardDto boardDto);

    // 단일 게시글 조회
    BoardDto findById(Long boardId);

    // 전체 게시글 조회
    List<BoardDto> findAll();

    // 게시글 수정
    BoardDto update(BoardDto boardDto);

    // 단일 게시글 삭제
    void delete(Long boardId);

    // 총 게시글 수 조회 (페이징)
    int count();

    // 페이지 조회 (페이징)
    List<BoardDto> getPage(Map<String, Integer> params);

    // 게시글 검색
    List<BoardDto> search(SearchCondition searchCondition);

    // 검색 결과 수 조회 (페이징)
    int searchCount(SearchCondition searchCondition);
}
