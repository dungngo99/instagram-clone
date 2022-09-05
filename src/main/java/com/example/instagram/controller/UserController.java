package com.example.instagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagram.model.ErrorResponse;
import com.example.instagram.model.User;
import com.example.instagram.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping
    public User create(@RequestBody User user) throws Exception {
        return userService.createUser(user);
    }

    @PutMapping("/me/password")
    public User updatePassword(@RequestBody User user) throws Exception {
        return userService.updatePassword(user);
    }

    @PutMapping("/me/profile")
    public User updateProfile(@RequestBody User user) throws Exception {
        return userService.updateProfile(user);
    }

    @DeleteMapping
    public String deleteUser(@RequestBody User user) throws Exception {
        userService.delete(user);
        return "Successfully deleted a user";
    }
}
