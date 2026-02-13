package com.ian.ch4.dao;

import com.ian.ch4.domain.BoardDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDaoImpl implements BoardDao {

    private final SqlSession session;

    String namespace = "com.ian.ch4.mapper.BoardMapper.";

    // 게시글 등록
    @Override
    public BoardDto save(BoardDto boardDto) {
        session.insert(namespace + "save", boardDto);

        return boardDto;
    }

    // 단일 게시글 조회
    @Override
    public BoardDto findById(Long boardId) {
        return session.selectOne(namespace + "findById", boardId);
    }

    // 전체 게시글 조회
    @Override
    public List<BoardDto> findAll() {
        return session.selectList(namespace + "findAll");
    }

    // 게시글 수정
    @Override
    public BoardDto update(BoardDto boardDto) {
        session.update(namespace + "update", boardDto);

        return boardDto;
    }

    // 단일 게시글 삭제
    @Override
    public void delete(BoardDto boardDto) {
        session.delete(namespace + "delete", boardDto);
    }
}
