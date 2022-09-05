package com.example.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instagram.model.JwtResponse;

@Repository
public interface JwtRepository extends JpaRepository<JwtResponse, Long> {
    public JwtResponse findByJwt(String jwt);
}
