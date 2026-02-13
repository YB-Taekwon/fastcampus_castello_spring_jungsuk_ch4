package com.ian.ch4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String username;
    private String password;
    private String name;
    private String email;
    private LocalDate birth;
    private String sns;
    private LocalDateTime createAt;

    public UserDto(String username, String password, String name, String email, LocalDate birth, String sns) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.sns = sns;
    }
}
