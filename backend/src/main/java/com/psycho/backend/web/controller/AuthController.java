package com.psycho.backend.web.controller;

import com.psycho.backend.data.dto.UserDto;
import com.psycho.backend.data.dto.validation.OnCreate;
import com.psycho.backend.data.mappers.UserMapper;
import com.psycho.backend.domain.user.User;
import com.psycho.backend.service.api.AuthService;
import com.psycho.backend.web.security.dto.JwtRequestDto;
import com.psycho.backend.web.security.dto.JwtResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.OK)
    public User register(@RequestBody @Validated(OnCreate.class) UserDto userDto) {;
        log.info("Accepted request from [/api/v1/auth/register] endpoint");
        return authService.register(userMapper.toEntity(userDto));
    }

    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponseDto login(@RequestBody @Validated JwtRequestDto jwtRequestDto) {
        log.info("Accepted request from [/api/v1/auth/login] endpoint");
        return authService.login(jwtRequestDto);
    }
}
