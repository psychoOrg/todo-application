package com.psycho.backend.web.security.factory;

import com.psycho.backend.domain.user.User;
import com.psycho.backend.web.security.JwtUserDetails;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUserDetailsFactory {
    public static JwtUserDetails build(final User user) {
        return new JwtUserDetails();
    }
}
