package com.psycho.backend.service.impl;

import com.psycho.backend.data.exception.ResourceNotFoundException;
import com.psycho.backend.domain.user.User;
import com.psycho.backend.repository.UserRepository;
import com.psycho.backend.service.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        log.info("Created user [{} id] with username [{}]", user.getId() , user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleted user [{} id]", id);
        userRepository.deleteById(id);
    }

    @Override
    public User getById(Long id) {
        log.info("Get user with [{} id]", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can`t find user with id: " + id));
    }

    @Override
    public User getByUsername(String username) {
        log.info("Get user with [{}] username", username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Can`t find user with username: " + username));
    }
}
