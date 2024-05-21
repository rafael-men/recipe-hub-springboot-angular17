package com.rafa.recipehub.config;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtProvider {
    public String generateToken(Authentication auth) {
        String jwt = Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email",auth.getName())
                .signWith().compact();
        return jwt;
    }
}
