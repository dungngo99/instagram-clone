package com.example.instagram.model;

public class JwtResponse {
    private String jwt;
    private String datetime;

    public String getJwt() {
        return jwt;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public JwtResponse(String jwt, String datetime) {
        this.jwt = jwt;
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "JwtResponse [datetime=" + datetime + ", jwt=" + jwt + "]";
    }

}
