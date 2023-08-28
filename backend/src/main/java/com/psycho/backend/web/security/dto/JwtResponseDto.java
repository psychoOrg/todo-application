package com.psycho.backend.web.security.dto;

import lombok.Data;

@Data
public class JwtResponseDto {
    private Long id;
    private String username;
    private String accessToken;
}
