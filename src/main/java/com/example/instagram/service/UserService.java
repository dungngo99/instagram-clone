package com.example.instagram.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagram.model.JwtResponse;
import com.example.instagram.model.User;
import com.example.instagram.repository.UserRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenService jwtTokenService;

    public User createUser(User user) throws Exception {
        User userFromDB = userRepository.findByUserName(user.getUserName());
        if (userFromDB != null)
            throw new Exception("User already existed");

        user.setUuid(UUID.randomUUID());
        user.setCreatedAt(LocalDate.now());
        return userRepository.save(user);
    }

    public User updatePassword(User user) throws Exception {
        if (user.getNewPassword() == null || user.getPassword() == null) {
            throw new Exception("Invalid request");
        }

        User userFromDB = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        if (userFromDB == null)
            throw new Exception("User not found");

        userFromDB.setPassword(user.getNewPassword());
        userFromDB.setNewPassword(user.getNewPassword());
        return userRepository.save(userFromDB);
    }

    public User updateProfile(User user) throws Exception {

        User userFromDB = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        if (userFromDB == null)
            throw new Exception("User not found");

        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setLastName(user.getLastName());
        userFromDB.setBirthDate(user.getBirthDate());

        return userRepository.save(userFromDB);
    }

    public User delete(User user) throws Exception {
        User userFromDB = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        if (userFromDB == null)
            throw new Exception("User not found");

        userRepository.delete(userFromDB);
        return userFromDB;
    }

    public JwtResponse login(User user) throws Exception {
        if (user.getPassword() == null || user.getUserName() == null)
            throw new Exception("Invalid username or password");

        User userFromDB = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        if (userFromDB == null)
            throw new Exception("User not found");

        userFromDB.setLoginAt(LocalDate.now());
        userFromDB = userRepository.save(userFromDB);

        String jwt = jwtTokenService.generateToken(userFromDB.getUuid());

        JwtResponse response = new JwtResponse(jwt);
        response.setCreated_at(LocalDateTime.now());
        response.setExpired_at(LocalDateTime.now().plusHours(1));
        response.setUuid(userFromDB.getUuid());
        response.setUser(userFromDB);

        return jwtTokenService.save(response);
    }

    public void logout(JwtResponse jwtResponse) {
        JwtResponse jwtResponseFromDB = jwtTokenService.findByJwt(jwtResponse.getJwt());
        if (jwtResponseFromDB == null)
            return;

        jwtResponseFromDB.setExpired_at(LocalDateTime.now());
        jwtTokenService.save(jwtResponseFromDB);
    }
}
