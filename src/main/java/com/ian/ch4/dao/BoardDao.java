package com.ian.ch4.dao;

import com.ian.ch4.domain.BoardDto;

import java.util.List;

public interface BoardDao {
    // 게시글 등록
    BoardDto save(BoardDto boardDto);

    // 단일 게시글 조회
    BoardDto findById(Long boardId);

    // 전체 게시글 조회
    List<BoardDto> findAll();

    // 게시글 수정
    BoardDto update(BoardDto boardDto);

    // 단일 게시글 삭제
    void delete(BoardDto boardDto);
}
