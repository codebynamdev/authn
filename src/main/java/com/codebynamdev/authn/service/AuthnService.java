package com.codebynamdev.authn.service;

import com.codebynamdev.authn.dto.AuthResponse;
import com.codebynamdev.authn.dto.LoginRequest;
import com.codebynamdev.authn.security.CustomUserDetails;
import com.codebynamdev.authn.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthnService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthnService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    public AuthResponse  authenticate(LoginRequest loginRequest) {
        try{
            // step - 1. create the Authentication object via AuthenticationManager by method type UserNamePassword only
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            //step - 2. take out the userDetails from the above created object
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            //step - 3. validate that the userDetails has the role which has come from controller end.
            boolean hasRole = userDetails.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_" + loginRequest.getRole()));

            if(!hasRole) {
                throw new RuntimeException("User does not have requested role : " + loginRequest.getRole());
            }

            //step - 4. Generate token
            String token = jwtUtil.generateToken(loginRequest.getEmail(), userDetails.getUser().getId(), loginRequest.getRole());

            //return token as response
            return new AuthResponse(token);

        }catch(AuthenticationException e) {
            throw new RuntimeException("Invalid email or Password", e);
        }
    }
}
