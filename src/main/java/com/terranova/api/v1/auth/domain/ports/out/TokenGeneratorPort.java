package com.terranova.api.v1.auth.domain.ports.out;

import java.util.List;

public interface TokenGeneratorPort {

    String generateToken(String identification, List<String> roles);

//    Claims extractAllClaims(String token);
//
//    String getIdentificationFromToken(String token);
//
//    void validateJwtToken(String token);
}
