package com.ian.ch4.dao;

import com.ian.ch4.domain.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final SqlSession session;

    String namespace = "com.ian.ch4.mapper.UserMapper.";

    // 사용자 등록
    @Override
    public UserDto save(UserDto userDto) {
        session.insert(namespace + "save", userDto);

        return userDto;
    }

    // 단일 사용자 조회
    @Override
    public UserDto findByUsername(String username) {
        return session.selectOne(namespace + "findByUsername", username);
    }

    // 사용자 수정
    @Override
    public UserDto update(UserDto userDto) {
        session.update(namespace + "update", userDto);

        return userDto;
    }

    // 단일 사용자 삭제
    @Override
    public void deleteByUsername(String username) {
        session.delete(namespace + "deleteByUsername", username);
    }
}
