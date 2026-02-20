package com.terranova.api.v1.auth.domain.model;

import java.util.List;

public record AuthenticatedUser(
        String identification,
        List<String> roles
){ }
