package com.ian.ch4.service;

import com.ian.ch4.dao.BoardDao;
import com.ian.ch4.domain.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void delete(BoardDto boardDto) {
        boardDao.delete(boardDto);
    }
}
