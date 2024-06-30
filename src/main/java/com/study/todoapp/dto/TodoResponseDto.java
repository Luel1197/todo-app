package com.study.todoapp.dto;

import lombok.Getter;

@Getter
public class TodoResponseDto {
    private Long id;

    private String title;

    private String content;

    public TodoResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
