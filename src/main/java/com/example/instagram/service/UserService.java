package com.example.instagram.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagram.model.User;
import com.example.instagram.repository.UserRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public User createUser(User user) throws Exception {
        List<User> userFromDBs = userRepository.findByUserNameIs(user.getUserName());
        if (userFromDBs.size() > 0)
            throw new Exception("User already existed");

        user.setUuid(UUID.randomUUID());
        user.setCreatedAt(LocalDate.now());
        return userRepository.save(user);
    }

    public User updatePassword(User user) throws Exception {
        User userFromDB = userRepository.getReferenceById(user.getUserID());
        if (userFromDB == null)
            throw new Exception("User not found");

        userFromDB.setPassword(user.getPassword());
        return userRepository.save(userFromDB);
    }

    public User updateProfile(User user) throws Exception {
        User userFromDB = userRepository.getReferenceById(user.getUserID());
        if (userFromDB == null)
            throw new Exception("User not found");

        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setLastName(user.getLastName());
        userFromDB.setBirthDate(user.getBirthDate());

        return userRepository.save(userFromDB);
    }

    public User delete(User user) throws Exception {
        User userFromDB = userRepository.getReferenceById(user.getUserID());
        if (userFromDB == null)
            throw new Exception("User not found");

        userRepository.delete(userFromDB);
        return userFromDB;
    }

    public String login(User user) throws Exception {
        if (user.getPassword() == null || user.getUserName() == null)
            throw new Exception("Invalid username or password");

        List<User> userFromDB = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        if (userFromDB.size() == 0)
            throw new Exception("User not found");

        userFromDB.get(0).setLoginAt(LocalDate.now());
        return jwtTokenUtil.generateToken(userFromDB.get(0).getUuid());
    }
}
