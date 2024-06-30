package com.study.todoapp.controller;

import com.study.todoapp.dto.TodoCreateRequestDto;
import com.study.todoapp.dto.TodoResponseDto;
import com.study.todoapp.dto.TodoUpdateRequestDto;
import com.study.todoapp.entity.Todo;
import com.study.todoapp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<String> addTodo(@RequestBody TodoCreateRequestDto requestDto) {
        Long id = todoService.create(requestDto);
        return ResponseEntity.ok(id + " Todo created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable Long id) {
        TodoResponseDto todoResponseDto = todoService.findTodoId(id);
        return ResponseEntity.ok(todoResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable Long id, @RequestBody TodoUpdateRequestDto requestDto) {
        Long updateId = todoService.update(id, requestDto);
        return ResponseEntity.ok(updateId + " Todo updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.ok("Todo deleted");
    }
}
