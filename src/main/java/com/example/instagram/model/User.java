package com.example.instagram.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true, allowGetters = true, allowSetters = true)
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "constraints", columnNames = { "user_id", "user_name" }) })
public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private String birthDate;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "login_at")
    private LocalDate loginAt;

    @Column(name = "logout_at")
    private LocalDate logoutAt;

    @Column(name = "uuid", updatable = false, nullable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerationStrategy")
    private UUID uuid;

    public User() {

    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public long getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getLoginAt() {
        return loginAt;
    }

    public LocalDate getLogoutAt() {
        return logoutAt;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setLoginAt(LocalDate loginAt) {
        this.loginAt = loginAt;
    }

    public void setLogoutAt(LocalDate logoutAt) {
        this.logoutAt = logoutAt;
    }

    public void setUuid(UUID token) {
        this.uuid = token;
    }

    @Override
    public String toString() {
        return "User [birthDate=" + birthDate + ", createdAt=" + createdAt + ", firstName=" + firstName + ", lastName="
                + lastName + ", loginAt=" + loginAt + ", logoutAt=" + logoutAt + ", password=" + password + ", userID="
                + userID + ", userName=" + userName + ", uuid=" + uuid + "]";
    }

}
