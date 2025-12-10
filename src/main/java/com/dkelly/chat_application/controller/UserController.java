package com.dkelly.chat_application.controller;


import com.dkelly.chat_application.dto.user.UserRequest;
import com.dkelly.chat_application.dto.user.UserResponse;
import com.dkelly.chat_application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse create(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }
}
