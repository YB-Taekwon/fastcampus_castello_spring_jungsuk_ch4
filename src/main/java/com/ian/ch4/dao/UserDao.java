package com.ian.ch4.dao;

import com.ian.ch4.domain.UserDto;

public interface UserDao {

    // 사용자 등록
    UserDto save(UserDto userDto);

    // 단일 사용자 조회
    UserDto findByUsername(String username);

    // 사용자 수정
    UserDto update(UserDto userDto);

    // 특정 사용자 삭제
    void deleteByUsername(String username);
}
