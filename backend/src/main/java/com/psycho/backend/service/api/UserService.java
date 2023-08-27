package com.psycho.backend.service.api;

import com.psycho.backend.data.dto.UserDto;
import com.psycho.backend.domain.user.User;

public interface UserService {
    User getByUsername(String username);

    User create(User user);

    void delete(Long id);

    User getById(Long id);
}
