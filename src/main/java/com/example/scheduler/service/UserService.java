package com.example.scheduler.service;

import com.example.scheduler.dto.UserRequestDto;
import com.example.scheduler.dto.UserResponseDto;

public interface UserService {
    UserResponseDto saveUser(UserRequestDto requestDto);
}
