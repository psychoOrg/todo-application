package com.psycho.backend.service.api;

import com.psycho.backend.domain.user.User;
import com.psycho.backend.web.security.dto.JwtRequestDto;
import com.psycho.backend.web.security.dto.JwtResponseDto;

public interface AuthService {

    User register(User user);
    JwtResponseDto login(JwtRequestDto jwtRequestDto);
}
