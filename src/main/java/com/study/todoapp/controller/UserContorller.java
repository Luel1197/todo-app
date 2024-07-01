package com.study.todoapp.controller;

import com.study.todoapp.dto.SignupRequestDto;
import com.study.todoapp.entity.User;
import com.study.todoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserContorller {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequestDto requestDto) {
        Long id = userService.signup(requestDto);
        return ResponseEntity.ok(id + "SignUp ok");
    }
}
