package com.codebynamdev.authn.controller;

import com.codebynamdev.authn.dto.DriverSignupRequest;
import com.codebynamdev.authn.dto.SignupRequest;
import com.codebynamdev.authn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/signup")
public class SignupController {
    private final UserService userService;
    public SignupController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/user")
    ResponseEntity<Map<String, String>>signUpAsUser(@RequestBody SignupRequest signupRequest) {
        Map<String, String>response = new HashMap<>();
        try{
            userService.registerUser(signupRequest);
            response.put("message", "user created successfully");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        }catch(Exception e) {
            System.out.println("issue in register as a new User with user_name: " + signupRequest.getName());
            response.put("message","issue im register as a new user");
            response.put("error", "Registration failed. Please try again.");
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(response);
        }
    }
    @PostMapping("/driver")
    ResponseEntity<Map<String, String>>signupAsDriver(@RequestBody DriverSignupRequest driverSignupRequest) {
        Map<String, String>response = new HashMap<>();
        try{
            userService.becomeDriver(driverSignupRequest);
            response.put("message", "Applied as a driver");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        }catch(Exception e) {
            System.out.println("issue in register as a Driver with user_name: ");
            response.put("message","issue in register as a driver");
            response.put("error", "Registration failed. Please try again.");
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(response);
        }
    }
}
