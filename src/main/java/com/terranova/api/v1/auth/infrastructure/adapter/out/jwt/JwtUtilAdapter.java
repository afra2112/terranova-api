package com.terranova.api.v1.auth.infrastructure.adapter.out.jwt;

import com.terranova.api.v1.auth.domain.ports.out.TokenGeneratorPort;
import com.terranova.api.v1.role.enums.RoleEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;


@Component
@AllArgsConstructor
public class JwtUtilAdapter implements TokenGeneratorPort {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    private SecretKey key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generateToken(String identification, List<String> roles){
        List<RoleEnum> rolesEnums = roles.stream().map(RoleEnum::valueOf).toList();
        return Jwts.builder()
                .claim("roles", rolesEnums)
                .setSubject(identification)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(key)
                .compact();
    }

//    @Override
//    public Claims extractAllClaims(String token){
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    @Override
//    public String getIdentificationFromToken(String token){
//        return Jwts.parserBuilder().setSigningKey(key).build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    @Override
//    public void validateJwtToken(String token){
//        try{
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//        }catch (ExpiredJwtException e){
//            throw new TokenExpiredException("Token expired");
//
//        }catch (JwtException e){
//            throw new InvalidJwtTokenException("Invalid Token");
//        }
//    }
}
