package com.terranova.api.v1.user.domain.ports.out;

import com.terranova.api.v1.user.domain.model.User;

public interface UserRepositoryPort {

    User save(User user);
}
