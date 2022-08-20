package com.example.instagram.service;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private static final Key SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String getUUIDFromToken(String token) {
        return getAllClaimsFromToken(token).getId();
    }

    public Date getExpirationDate(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    public String generateToken(UUID uuid) {
        return Jwts
                .builder()
                .setId(uuid.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SECRET)
                .compact();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDate(token);
        return expiration.before(new Date());
    }

    public Boolean isValidToken(String token, UUID uuid) {
        return getUUIDFromToken(token).equals(uuid.toString()) && !isTokenExpired(token);
    }

}
