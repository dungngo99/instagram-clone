package com.example.instagram.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instagram.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserNameIs(String userName);

    List<User> findByUserNameAndPassword(String userName, String password);
}
