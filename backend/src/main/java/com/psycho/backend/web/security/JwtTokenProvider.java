package com.psycho.backend.web.security;

import com.psycho.backend.service.api.UserService;
import com.psycho.backend.service.props.JwtProperty;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final UserService userService;
    private final JwtProperty jwtProperty;
    private Key key;

    @PostConstruct
    private void init() {
        this.key = Keys.hmacShaKeyFor(jwtProperty.getSecret().getBytes());
    }
}
