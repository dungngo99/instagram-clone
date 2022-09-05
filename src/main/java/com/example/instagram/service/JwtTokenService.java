package com.example.instagram.service;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.instagram.model.JwtResponse;
import com.example.instagram.repository.JwtRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenService implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;
    private static final long JWT_TOKEN_VALIDITY = 1 * 60 * 60;

    private static final Key SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Autowired
    private JwtRepository jwtRepository;

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

    public Boolean isValidToken(String token, String uuid) {
        return getUUIDFromToken(token).equals(uuid) && !isTokenExpired(token);
    }

    public JwtResponse save(JwtResponse jwtResponse) {
        return jwtRepository.save(jwtResponse);
    }

    public boolean containsUUID(String token) {
        JwtResponse jwt = jwtRepository.findByJwt(token);
        return jwt != null ? true : false;
    }

    public JwtResponse findByJwt(String token) {
        return jwtRepository.findByJwt(token);
    }
}
