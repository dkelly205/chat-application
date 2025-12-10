package com.dkelly.chat_application.service;

import com.dkelly.chat_application.dto.user.UserRequest;
import com.dkelly.chat_application.dto.user.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);
}
