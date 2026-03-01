package com.codebynamdev.authn.dto;

import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.MulticastChannel;

public class DriverSignupRequest {
    private String licenseNumber;
    private String aadharNumber;
    private MultipartFile licenseDocument;
    private MultipartFile aadharDocument;
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleModel;
    private String phoneNumber;
    private String address;


    //Getter

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public MultipartFile getLicenseDocument() {
        return licenseDocument;
    }

    public MultipartFile getAadharDocument() {
        return aadharDocument;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}
