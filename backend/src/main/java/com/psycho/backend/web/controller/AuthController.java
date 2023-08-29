package com.psycho.backend.web.controller;

import com.psycho.backend.data.dto.UserDto;
import com.psycho.backend.data.dto.validation.OnCreate;
import com.psycho.backend.data.mappers.UserMapper;
import com.psycho.backend.domain.user.User;
import com.psycho.backend.service.api.AuthService;
import com.psycho.backend.web.security.dto.JwtRequestDto;
import com.psycho.backend.web.security.dto.JwtResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.OK)
    public User register(@RequestBody @Validated(OnCreate.class) UserDto userDto) {
        return authService.register(userMapper.toEntity(userDto));
    }

    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponseDto login(@RequestBody @Validated JwtRequestDto jwtRequestDto) {
        return authService.login(jwtRequestDto);
    }
}
