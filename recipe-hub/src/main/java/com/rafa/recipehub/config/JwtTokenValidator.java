package com.rafa.recipehub.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtTokenValidator extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenValidator.class);

    // header constant
    public static final String JWT_HEADER = "Authorization";
    private static final String SECRET_KEY = "5365sjmk2nkijdn2ouo524nl24nj"; // Consider storing securely

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(JWT_HEADER);

        if (jwt != null && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY.getBytes())
                        .parseClaimsJws(jwt)
                        .getBody();

                String email = String.valueOf(claims.get("email"));
                logger.debug("JWT claims extracted, email: {}", email);

                if (email != null && !email.isEmpty()) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, null);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    logger.error("Email claim is null or empty in JWT");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token: Missing email claim");
                    return;
                }
            } catch (Exception e) {
                logger.error("Invalid JWT token", e);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
                return;
            }
        } else {
            logger.warn("JWT Token is missing or does not start with Bearer String");
        }

        filterChain.doFilter(request, response);
    }
}

