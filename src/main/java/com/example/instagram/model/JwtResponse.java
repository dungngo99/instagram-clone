package com.example.instagram.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jwt")
public class JwtResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String jwt;
    private UUID uuid;
    private LocalDateTime created_at;
    private LocalDateTime expired_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public JwtResponse(String jwt, LocalDateTime created_at) {
        this.jwt = jwt;
        this.created_at = created_at;
    }

    public JwtResponse(String jwt, UUID uuid, LocalDateTime created_at) {
        this.jwt = jwt;
        this.uuid = uuid;
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "JwtResponse [created_at=" + created_at + ", expired_at=" + expired_at + ", id=" + id + ", jwt=" + jwt;
    }

    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }

    public JwtResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getExpired_at() {
        return expired_at;
    }

    public void setExpired_at(LocalDateTime expired_at) {
        this.expired_at = expired_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    
}
