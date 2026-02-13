package com.ian.ch4.dao;

import com.ian.ch4.domain.BoardDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:/root-context-test.xml")
class BoardDaoTest {

    @Autowired
    BoardDao boardDao;

    @Test
    void saveAndFindById() {
        // given
        BoardDto board = boardDao.save(new BoardDto("title", "content", "writer"));

        // when
        BoardDto result = boardDao.findById(board.getBoardId());

        // then
        assertThat(result.getBoardId()).isEqualTo(board.getBoardId());
        assertThat(result.getTitle()).isEqualTo(board.getTitle());
        assertThat(result.getContent()).isEqualTo(board.getContent());
        assertThat(result.getWriter()).isEqualTo(board.getWriter());
        assertThat(result.getViewCount()).isEqualTo(0);
        assertThat(result.getCommentCount()).isEqualTo(0);
        assertThat(result.getCreatedAt()).isNotNull();
        assertThat(result.getUpdatedAt()).isNotNull();
    }

    @Test
    void findAll() {
        // given
        BoardDto board1 = boardDao.save(new BoardDto("title1", "content1", "writer1"));
        BoardDto board2 = boardDao.save(new BoardDto("title2", "content2", "writer2"));

        // when
        List<BoardDto> boards = boardDao.findAll();

        // then
        assertThat(boards).hasSize(2);
        assertThat(boards).extracting(BoardDto::getBoardId).contains(board1.getBoardId(), board2.getBoardId());
    }

    @Test
    void update() {
        // given
        BoardDto before = boardDao.save(new BoardDto("title", "content", "writer"));
        BoardDto board = boardDao.findById(before.getBoardId());

        // when
        board.setTitle("new title");
        board.setContent("new content");
        board.setWriter("new writer");

        BoardDto after = boardDao.update(board);

        // then
        assertThat(after.getBoardId()).isEqualTo(board.getBoardId());
        assertThat(after.getTitle()).isEqualTo("new title");
        assertThat(after.getContent()).isEqualTo("new content");
        assertThat(after.getWriter()).isEqualTo("new writer");
    }
}