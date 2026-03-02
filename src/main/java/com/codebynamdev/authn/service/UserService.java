package com.codebynamdev.authn.service;

import com.codebynamdev.authn.dto.DriverSignupRequest;
import com.codebynamdev.authn.dto.SignupRequest;
import com.codebynamdev.authn.entity.*;
import com.codebynamdev.authn.repository.DriverDocumentRepository;
import com.codebynamdev.authn.repository.DriverProfileRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.codebynamdev.authn.repository.RoleRepository;
import com.codebynamdev.authn.repository.UserRepository;

import java.io.IOException;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private DriverProfileRepository driverProfileRepository;
    private DriverDocumentRepository driverDocumentRepository;
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, DriverProfileRepository driverProfileRepository, DriverDocumentRepository driverDocumentRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.driverProfileRepository = driverProfileRepository;
        this.driverDocumentRepository = driverDocumentRepository;
    }
    public void registerUser(SignupRequest signupRequest) {
        if(userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Email already Exist");
        }
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setMobile(signupRequest.getMobile());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByName("USER").
                orElseThrow(() -> {return new RuntimeException("Role Not Found");});
        user.getRoles().add(userRole);
        userRepository.save(user);
    }
    public void becomeDriver(DriverSignupRequest driverSignupRequest) throws IOException {
        User user = userRepository.findByemail(driverSignupRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with the provided email_id"));
        if(!user.getPassword().equals(passwordEncoder.encode(driverSignupRequest.getPassword()))) {
            throw new RuntimeException("Password not match");
        }
        boolean alreadyDriver = user.getRoles().stream()
                .anyMatch(role -> role.getName().equals("DRIVER"));
        if(alreadyDriver) {
            throw new RuntimeException("Already Exist profile with Driver");
        }
        Role driverRole = roleRepository.findByName("DRIVER")
                .orElseThrow(() -> new RuntimeException("Driver profile can't initiated from Admin side"));
        user.getRoles().add(driverRole);


        DriverDocument driverDocument = new DriverDocument();
        driverDocument.setAadhaarDocument(driverSignupRequest.getAadharDocument().getBytes());
        driverDocument.setLicenseDocument(driverSignupRequest.getLicenseDocument().getBytes());
        driverDocument.setAadhaarNumber(driverSignupRequest.getAadharNumber());

        DriverProfile driverProfile = new DriverProfile();
        driverProfile.setLicenseNumber(driverSignupRequest.getLicenseNumber());
        driverProfile.setVehicleModel(driverSignupRequest.getVehicleModel());
        driverProfile.setVehicleNumber(driverSignupRequest.getVehicleNumber());
        driverProfile.setVehicleType(driverSignupRequest.getVehicleType());
        driverProfile.setStatus(DriverStatus.PENDING);
        driverProfile.setUser(user);


        driverDocument.setDriverProfile(driverProfile);
        driverProfile.setDriverDocument(driverDocument);

        driverProfileRepository.save(driverProfile);
    }
}