package com.terranova.api.v1.auth.domain.ports.out;

import com.terranova.api.v1.auth.domain.model.AuthenticatedUser;
import com.terranova.api.v1.auth.domain.model.UserCredential;

public interface AuthenticationPort {

    AuthenticatedUser authenticate(UserCredential credentials);
}
