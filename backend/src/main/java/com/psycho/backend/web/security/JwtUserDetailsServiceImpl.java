package com.psycho.backend.web.security;

import com.psycho.backend.service.api.UserService;
import com.psycho.backend.web.security.factory.JwtUserDetailsFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return JwtUserDetailsFactory.build(userService.getByUsername(username));
    }
}
