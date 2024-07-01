package com.study.todoapp.service;

import com.study.todoapp.dto.CommentCreateRequestDto;
import com.study.todoapp.dto.CommentUpdateRequestDto;
import com.study.todoapp.entity.Comment;
import com.study.todoapp.entity.Todo;
import com.study.todoapp.entity.User;
import com.study.todoapp.enums.UserRoleEnum;
import com.study.todoapp.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final TodoService todoService;
    private final CommentRepository commentRepository;

    public Comment findById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()->new RuntimeException("입력하신 댓글 아이디가 없습니다."));
        return comment;
    }

    public Boolean validateUser(Comment comment, User user) {
        if (comment.getUser().getId() != user.getId()) {
            if (user.getRole() != UserRoleEnum.ADMIN){
                return true;
            }
            return true;
        }
        return false;
    }

    public CommentService(TodoService todoService, CommentRepository commentRepository) {
        this.todoService = todoService;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public void create(CommentCreateRequestDto requestDto, User user) {
        Todo todo = todoService.findById(requestDto.getTodoId());

        Comment comment = new Comment(requestDto.getText(), user, todo);

        commentRepository.save(comment);
    }

    @Transactional
    public Long update(Long id, CommentUpdateRequestDto requestDto, User user) {
        Comment comment = findById(id);

        if (validateUser(comment, user)) {
            throw new RuntimeException("자신의 댓글만 수정 할 수 있습니다.");
        }

        comment.Update(requestDto.getText());

        return comment.getId();
    }

    public void delete(Long id, User user) {
        Comment comment = findById(id);
        if (validateUser(comment, user)) {
            throw new RuntimeException("자신의 댓글만 삭제 할 수 있습니다.");
        }
        commentRepository.delete(comment);
    }
}
