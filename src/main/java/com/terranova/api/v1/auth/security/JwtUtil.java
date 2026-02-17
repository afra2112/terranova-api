package com.terranova.api.v1.auth.security;

import com.terranova.api.v1.common.enums.ErrorCodeEnum;
import com.terranova.api.v1.common.exception.BusinessException;
import com.terranova.api.v1.role.enums.RoleEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;


@Component
public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String identification, List<RoleEnum> roles){
        return Jwts.builder()
                .claim("roles", roles)
                .setSubject(identification)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(key)
                .compact();
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getIdentificationFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public void validateJwtToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        }catch (ExpiredJwtException e){
            throw new BusinessException(ErrorCodeEnum.TOKEN_EXPIRED, "Token expired");

        }catch (JwtException e){
            throw new BusinessException(ErrorCodeEnum.INVALID_TOKEN, "Invalid Token");
        }
    }
}
