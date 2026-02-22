package com.terranova.api.v1.auth.domain.ports.out;

import com.terranova.api.v1.auth.domain.model.AuthenticatedUser;
import com.terranova.api.v1.auth.domain.model.NewUserDomain;

public interface UserRegisterPort {

    boolean existByEmailOrIdentification(String email, String identification);
    AuthenticatedUser createUser(NewUserDomain newUserDomain);
}
