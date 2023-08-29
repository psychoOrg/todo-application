package com.psycho.backend.web.controller;

import com.psycho.backend.data.dto.UserDto;
import com.psycho.backend.data.dto.validation.OnCreate;
import com.psycho.backend.data.mappers.UserMapper;
import com.psycho.backend.domain.user.User;
import com.psycho.backend.service.api.AuthService;
import com.psycho.backend.web.security.dto.JwtRequestDto;
import com.psycho.backend.web.security.dto.JwtResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    public User register(@RequestBody @Validated(OnCreate.class) UserDto userDto) {
        return authService.register(userMapper.toEntity(userDto));
    }

    public JwtResponseDto login(@RequestBody @Validated JwtRequestDto jwtRequestDto) {
        return authService.login(jwtRequestDto);
    }
}
