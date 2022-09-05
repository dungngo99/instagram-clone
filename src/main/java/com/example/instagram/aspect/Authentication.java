package com.example.instagram.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.instagram.model.ErrorResponse;
import com.example.instagram.service.JwtTokenService;

@Aspect
@Component
public class Authentication {
    @Autowired
    JwtTokenService jwtTokenService;

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @Pointcut("execution(* com.example.instagram.controller.UserController..*(..))")
    public void authenticateBeforeUserController() {
    }

    @Pointcut("execution(* com.example.instagram.controller.UserController.create(..))")
    public void createUserFromUserController() {
    }

    @Before("authenticateBeforeUserController() && ! createUserFromUserController()")
    public void authenticate(JoinPoint joinPoint) throws Exception {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attrs.getRequest();
        String token = request.getHeader("Authorization");

        if (token == null || !jwtTokenService.containsUUID(token))
            throw new Exception("Invalid token");
        else {
            String uuid = jwtTokenService.getUUIDFromToken(token);
            if (!jwtTokenService.isValidToken(token, uuid))
                throw new Exception("Invalid token");
        }
    }
}
