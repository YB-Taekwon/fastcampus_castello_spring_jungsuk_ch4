package com.ian.ch4.dao;

import com.ian.ch4.domain.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:/root-context-test.xml")
class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    void saveAndFindByUsername() {
        // given
        UserDto user = userDao.save(
                new UserDto("username", "password", "name", "email.example.com",
                        LocalDate.of(2020, 02, 02), "facebook")
        );

        // when
        UserDto result = userDao.findByUsername(user.getUsername());

        // then
        assertThat(result.getUsername()).isEqualTo(user.getUsername());
        assertThat(result.getPassword()).isEqualTo(user.getPassword());
        assertThat(result.getName()).isEqualTo(user.getName());
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
        assertThat(result.getBirth()).isEqualTo(user.getBirth());
        assertThat(result.getSns()).isEqualTo(user.getSns());
    }

    @Test
    void update() {
        // given
        UserDto before = userDao.save(
                new UserDto("username", "password", "name", "email.example.com",
                        LocalDate.of(2020, 02, 02), "facebook")
        );

        before.setEmail("newEmail@example.com");
        before.setSns("instagram");

        // when
        UserDto after = userDao.update(before);

        // then
        assertThat(after.getEmail()).isEqualTo("newEmail@example.com");
        assertThat(after.getSns()).isEqualTo("instagram");
    }
}