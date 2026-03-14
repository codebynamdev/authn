package com.codebynamdev.authn.controller;

import com.codebynamdev.authn.dto.AuthResponse;
import com.codebynamdev.authn.dto.LoginRequest;
import com.codebynamdev.authn.service.AuthnService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthnService authnService;
    public AuthController(AuthnService authnService) {
        this.authnService = authnService;
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authnService.authenticate(loginRequest);
        return ResponseEntity.ok(authResponse);
    }
}