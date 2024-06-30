package com.study.todoapp.service;

import com.study.todoapp.dto.TodoResponseDto;
import com.study.todoapp.dto.TodoUpdateRequestDto;
import com.study.todoapp.repository.TodoRepository;
import com.study.todoapp.dto.TodoCreateRequestDto;
import com.study.todoapp.entity.Todo;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Todo findById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("선택한 유저가 없다."));
        return todo;
    }

    @Transactional
    public Long create(TodoCreateRequestDto requestDto) {
        Todo todo = new Todo(requestDto.getTitle(), requestDto.getContent());
        todoRepository.save(todo);
        return todo.getId();
    }

    public TodoResponseDto findTodoId(Long id) {
        Todo todo = findById(id);
        TodoResponseDto todoResponseDto = new TodoResponseDto(todo.getId(), todo.getTitle(), todo.getContent());
        return todoResponseDto;
    }

    @Transactional
    public Long update(Long id, TodoUpdateRequestDto requestDto) {
        if (!Objects.equals(id,requestDto.getId())) {
            throw new RuntimeException("수정하려는 사용자가 아닙니다.");
        }
        Todo todo = findById(id);
        todo.update(requestDto.getTitle(), requestDto.getContent());
        todoRepository.save(todo);

        return todo.getId();
    }

    public void delete(Long id) {
        Todo todo = findById(id);
        todoRepository.delete(todo);
    }
}
