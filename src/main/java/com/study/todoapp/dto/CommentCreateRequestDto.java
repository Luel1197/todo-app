package com.study.todoapp.dto;

import lombok.Getter;

@Getter
public class CommentCreateRequestDto {
    private Long todoId;
    private String text;
}
