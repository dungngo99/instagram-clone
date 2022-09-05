package com.example.instagram.controller;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagram.model.ErrorResponse;
import com.example.instagram.model.Greeting;
import com.example.instagram.model.JwtResponse;
import com.example.instagram.model.User;
import com.example.instagram.service.JwtTokenService;
import com.example.instagram.service.UserService;

@RestController
public class GreetingController {
    private static final AtomicLong COUNTER = new AtomicLong();

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "hello") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String content) {
        return new Greeting(COUNTER.incrementAndGet(), String.format("Hello %s", content));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestBody User user) throws Exception {

        if (token != null) {
            JwtResponse jwtResponse = jwtTokenService.findByJwt(token);
            if (jwtResponse != null)
                return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.ACCEPTED);
        }

        JwtResponse jwtResponse = userService.login(user);

        if (jwtResponse == null) {
            return new ResponseEntity<JwtResponse>(
                    new JwtResponse("", LocalDateTime.now()),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/logout")
    public String logout(@RequestBody JwtResponse jwtResponse) {
        userService.logout(jwtResponse);
        return "sucessfully logged out";
    }

}
