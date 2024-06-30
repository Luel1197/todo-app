package com.study.todoapp.dto;

import lombok.Getter;

@Getter
public class TodoUpdateRequestDto {
    private Long id;
    private String title;
    private String content;

    public TodoUpdateRequestDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
