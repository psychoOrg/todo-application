package com.psycho.backend.service.impl;

import com.psycho.backend.aop.api.Loggable;
import com.psycho.backend.data.exception.ResourceMappingException;
import com.psycho.backend.domain.user.Role;
import com.psycho.backend.domain.user.User;
import com.psycho.backend.repository.UserRepository;
import com.psycho.backend.service.api.AuthService;
import com.psycho.backend.service.api.UserService;
import com.psycho.backend.web.security.JwtTokenProvider;
import com.psycho.backend.web.security.dto.JwtRequestDto;
import com.psycho.backend.web.security.dto.JwtResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    @Loggable
    public User register(User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ResourceMappingException(String.format("User [%s] already exist", user.getUsername()));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roleSet = Set.of(Role.ROLE_USER);
        user.setRoles(roleSet);

        user.setIsAccountNonExpired(Boolean.TRUE);
        user.setIsEnabled(Boolean.TRUE);
        user.setIsCredentialsNonExpired(Boolean.TRUE);
        user.setIsAccountNonLocked(Boolean.TRUE);

//        user.setCreatedAt(LocalDateTime.now());

        return userService.create(user);
    }

    @Override
    public JwtResponseDto login(JwtRequestDto jwtRequestDto) {
        JwtResponseDto jwtResponseDto = new JwtResponseDto();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtRequestDto.getUsername(), jwtRequestDto.getPassword()
                )
        );


        User user = userService.getByUsername(jwtRequestDto.getUsername());
        jwtResponseDto.setId(user.getId());
        jwtResponseDto.setUsername(user.getUsername());
        jwtResponseDto.setAccessToken(jwtTokenProvider.generateAccessToken(user));
        return jwtResponseDto;
    }
}
