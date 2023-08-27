package com.psycho.backend.web.security.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JwtRequestDto {
    @NotNull(message = "Username can`t be null")
    private String username;

    @NotNull(message = "Password can`t be null")
    private String password;
}
