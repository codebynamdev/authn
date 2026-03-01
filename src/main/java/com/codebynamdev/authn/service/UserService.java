package com.codebynamdev.authn.service;

import com.codebynamdev.authn.dto.SignupRequest;
import com.codebynamdev.authn.entity.Role;
import com.codebynamdev.authn.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.codebynamdev.authn.repository.RoleRepository;
import com.codebynamdev.authn.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void registerUser(SignupRequest signupRequest) {
        if(userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Email already Exist");
        }
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setMobile(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByName("USER").
                orElseThrow(() -> {return new RuntimeException("Role Not Found");});
        user.getRoles().add(userRole);
        userRepository.save(user);
    }

}