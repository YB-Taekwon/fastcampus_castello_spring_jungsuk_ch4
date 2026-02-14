package com.ian.ch4.domain;

import com.ian.ch4.dao.BoardDao;
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
class SearchConditionTest {

    @Autowired
    BoardDao boardDao;

    @Test
    void searchTest() {
        // given
        for (int i = 1; i <= 20; i++) {
            boardDao.save(
                    new BoardDto("title" + i, "content" + i, "writer" + i)
            );
        }

        // when
        List<BoardDto> boardList = boardDao.search(
                new SearchCondition("title2", "T", 1, 10) // title2%
        );

        // then
        assertThat(boardList).hasSize(2); // title2, title20
    }

    @Test
    void searchCountTest() {
        // given
        for (int i = 1; i <= 20; i++) {
            boardDao.save(
                    new BoardDto("title" + i, "content" + i, "writer" + i)
            );
        }

        // when
        int i = boardDao.searchCount(
                new SearchCondition("title2", "T", 1, 10) // title2%
        );

        // then
        assertThat(i).isEqualTo(2); // title2, title20
    }
}