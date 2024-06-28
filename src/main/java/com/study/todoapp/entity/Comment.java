package com.study.todoapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    // 사용자 아이디
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 일정 아이디
    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    private LocalDateTime createdAt;


}
