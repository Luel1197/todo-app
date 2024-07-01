package com.study.todoapp.service;

import com.study.todoapp.dto.SignupRequestDto;
import com.study.todoapp.entity.User;
import com.study.todoapp.enums.UserRoleEnum;
import com.study.todoapp.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 어드민 토큰
    private final  String ADMIN_TOKEN = "어드민테스트";

    public Long signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            throw new RuntimeException("아이디가 존재합니다.");
        }

        // 유저, 어드민 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new RuntimeException("관리자 암호가 틀렸습니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password,
            requestDto.getName(), role);

        userRepository.save(user);

        return user.getId();
    }
}
