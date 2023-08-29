package com.psycho.backend.data.dto;

import com.psycho.backend.data.dto.validation.OnCreate;
import com.psycho.backend.data.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {

    @NotNull(message = "Field [id] can not be null", groups = {OnUpdate.class})
    private Long id;

    @NotNull(message = "Field [username] can not be null", groups = {OnCreate.class})
    private String username;

    @NotNull(message = "Field [password] can not be null", groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @NotNull(message = "Field [firstname] can not be null", groups = {OnCreate.class, OnUpdate.class})
    private String firstname;
    @NotNull(message = "Field [lastname] can not be null", groups = {OnCreate.class, OnUpdate.class})
    private String lastname;

    private String email;
    private String phone;

    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;
}
