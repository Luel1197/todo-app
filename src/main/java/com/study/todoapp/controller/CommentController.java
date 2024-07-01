package com.study.todoapp.controller;

import com.study.todoapp.dto.CommentCreateRequestDto;
import com.study.todoapp.dto.CommentUpdateRequestDto;
import com.study.todoapp.secuirty.UserDetailsImpl;
import com.study.todoapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> addComment(@RequestBody CommentCreateRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.create(requestDto, userDetails.getUser());
        return ResponseEntity.ok("Comment created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateComment(@PathVariable  Long id, @RequestBody CommentUpdateRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long commentId = commentService.update(id, requestDto, userDetails.getUser());
        return ResponseEntity.ok(commentId + " Comment updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable  Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.delete(id, userDetails.getUser());
        return ResponseEntity.ok("Comment deleted");
    }
}
