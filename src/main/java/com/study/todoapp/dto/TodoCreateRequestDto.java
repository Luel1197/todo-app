package com.study.todoapp.dto;

import lombok.Getter;

@Getter
public class TodoCreateRequestDto {
    private String title;
    private String content;

    public TodoCreateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
