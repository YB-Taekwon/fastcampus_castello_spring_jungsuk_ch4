package com.ian.ch4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long boardId;
    private String title;
    private String content;
    private String writer;
    private Long viewCount;
    private Long commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BoardDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
