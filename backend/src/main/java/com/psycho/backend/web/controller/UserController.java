package com.psycho.backend.web.controller;


import com.psycho.backend.data.mappers.UserMapper;
import com.psycho.backend.domain.user.User;
import com.psycho.backend.service.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getById(@PathVariable Long id) {
        log.info("Accepted request from GET [/api/v1/user/{}] endpoint", id);
        return userService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        log.info("Accepted request from DELETE [/api/v1/auth/{}] endpoint", id);
        userService.delete(id);
    }
}
