package com.psycho.backend.web.security;

import com.psycho.backend.domain.user.User;
import com.psycho.backend.service.api.UserService;
import com.psycho.backend.service.props.JwtProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final UserDetailsService userDetailsService;
    private final JwtProperty jwtProperty;
    private Key key;

    @PostConstruct
    private void init() {
        this.key = Keys.hmacShaKeyFor(jwtProperty.getSecret().getBytes());
    }

    public String generateAccessToken(final User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("id", user.getId());

        Instant instant = Instant.now()
                .plus(jwtProperty.getLifetime(), ChronoUnit.HOURS);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(Date.from(instant))
                .signWith(key)
                .toString();
    }

    public String extractUsername(final String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody()
                .toString();
    }

    public String extractUserId(final String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody()
                .get("id")
                .toString();
    }

    public boolean validateAccessToken(final String accessToken) {
        Jws<Claims> claimsJws = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build().parseClaimsJws(accessToken);
        return !claimsJws.getBody().getExpiration().before(new Date());
    }

     public Authentication getAuthentication(final String accessToken) {
        String username = extractUsername(accessToken);
         UserDetails userDetails = userDetailsService.loadUserByUsername(username);

         return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
     }
}
