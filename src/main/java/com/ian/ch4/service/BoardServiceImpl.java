package com.ian.ch4.service;

import com.ian.ch4.dao.BoardDao;
import com.ian.ch4.domain.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDao boardDao;

    // 게시글 등록
    @Override
    public BoardDto save(BoardDto boardDto) {
        return boardDao.save(boardDto);
    }

    // 단일 게시글 조회
    @Override
    public BoardDto findById(Long boardId) {
        return boardDao.findById(boardId);
    }

    // 전체 게시글 조회
    @Override
    public List<BoardDto> findAll() {
        return boardDao.findAll();
    }

    // 게시글 수정
    @Override
    public BoardDto update(BoardDto boardDto) {
        return boardDao.update(boardDto);
    }

    // 단일 게시글 삭제
    @Override
    public void delete(Long boardId) {
        boardDao.delete(boardId);
    }

    // 총 게시글 수 조회 (페이징)
    @Override
    public int count() {
        return boardDao.count();
    }

    // 페이지 조회 (페이징)
    @Override
    public List<BoardDto> getPage(Map<String, Integer> params) {
        return boardDao.getPage(params);
    }
}
