package com.rafa.recipehub.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    // Chave secreta para assinatura do JWT
    private static final String SECRET_KEY = "5365sjmk2nkijdn2ouo524nl24nj";

    public String generateToken(Authentication auth) {
        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", auth.getName())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                .compact();
        return jwt;
    }

    public String getEmailfromJwtToken(String jwt) {
        try {
            if (jwt != null && jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7);
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY.getBytes())
                        .parseClaimsJws(jwt)
                        .getBody();

                String email = String.valueOf(claims.get("email"));
                return email;
            } else {
                logger.warn("JWT Token does not start with Bearer string");
                return null;
            }
        } catch (Exception e) {
            logger.error("Error parsing JWT token", e);
            return null;
        }
    }
}
